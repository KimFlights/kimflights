package com.kimgroup.kimflights.payment.service.impl;

import com.kimgroup.kimflights.payment.service.PaymentService;
import com.kimgroup.kimflights.payment.model.Transaction;
import com.kimgroup.kimflights.payment.repository.TransactionRepository;
import com.kimgroup.kimflights.payment.dto.PaymentRequestDTO;
import com.kimgroup.kimflights.payment.dto.TransactionDTO;
import com.kimgroup.kimflights.payment.repository.InvoiceRepository;
import com.kimgroup.kimflights.payment.model.Invoice;
import com.kimgroup.kimflights.payment.model.InvoiceStatus;
import com.kimgroup.kimflights.payment.exception.InvoiceNotFoundException;
import com.kimgroup.kimflights.payment.exception.TransactionNotFoundException;
import com.kimgroup.kimflights.payment.model.CardBrand;
import com.kimgroup.kimflights.payment.mapper.TransactionMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final TransactionRepository transactionRepository;
    private final InvoiceRepository invoiceRepository;
    private final TransactionMapper transactionMapper;
    private final SecureRandom random = new SecureRandom();

    public PaymentServiceImpl(TransactionRepository transactionRepository, InvoiceRepository invoiceRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.invoiceRepository = invoiceRepository;
        this.transactionMapper = transactionMapper;
    }

    public String getCardBrand(String cardNumber) {
        return CardBrand.fromCardNumber(cardNumber).name();
    }

    public TransactionDTO processPayment(PaymentRequestDTO request) {
        Invoice invoice = invoiceRepository.findById(request.invoiceId())
                .orElseThrow(() -> new InvoiceNotFoundException(request.invoiceId()));

        try {
            int sleepTime = 1000 + random.nextInt(2000);
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Transaction transaction = new Transaction();
        transaction.setCardNumber(maskCardNumber(request.cardNumber()));
        transaction.setBrand(getCardBrand(request.cardNumber()));
        transaction.setAmount(invoice.getCost());
        transaction.setCreatedAt(LocalDateTime.now());

        // 20% chance of failure
        if (random.nextInt(100) < 20) {
            transaction.setStatus("FAILED");
            transaction.setFailureReason(getRandomFailureReason());
            invoice.setStatus(InvoiceStatus.DECLINED);
        } else {
            transaction.setStatus("SUCCESS");
            invoice.setStatus(InvoiceStatus.COMPLETED);
        }

        invoiceRepository.save(invoice);
        Transaction saved = transactionRepository.save(transaction);
        return transactionMapper.toDTO(saved);
    }

    public TransactionDTO getTransaction(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));
        return transactionMapper.toDTO(transaction);
    }


    private String getRandomFailureReason() {
        String[] reasons = {"Insufficient funds", "Invalid card", "Card expired", "Suspected fraud"};
        return reasons[random.nextInt(reasons.length)];
    }

    private String maskCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.length() < 4) {
            return "****";
        }
        return "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
    }
}
