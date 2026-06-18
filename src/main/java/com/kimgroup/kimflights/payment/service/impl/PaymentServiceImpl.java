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
import com.kimgroup.kimflights.notification.NotificationService;
import com.kimgroup.kimflights.payment.exception.PaymentFailedException;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final TransactionRepository transactionRepository;
    private final InvoiceRepository invoiceRepository;
    private final TransactionMapper transactionMapper;
    private final NotificationService notificationService;
    private final SecureRandom random = new SecureRandom();

    public PaymentServiceImpl(TransactionRepository transactionRepository, InvoiceRepository invoiceRepository,
            TransactionMapper transactionMapper, NotificationService notificationService) {
        this.transactionRepository = transactionRepository;
        this.invoiceRepository = invoiceRepository;
        this.transactionMapper = transactionMapper;
        this.notificationService = notificationService;
    }

    public String getCardBrand(String bin) {
        return CardBrand.fromBin(bin).name();
    }

    public TransactionDTO processPayment(PaymentRequestDTO request) {
        Invoice invoice = invoiceRepository.findById(request.invoiceId())
                .orElseThrow(() -> new InvoiceNotFoundException(request.invoiceId()));

        try {
            int sleepTime = 5000 + random.nextInt(2000);
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Transaction transaction = new Transaction();
        transaction.setCardNumber(maskCardNumber(request.cardNumber()));
        String bin = request.cardNumber() != null && request.cardNumber().length() >= 6
                ? request.cardNumber().substring(0, 6)
                : request.cardNumber();
        transaction.setBrand(getCardBrand(bin));
        transaction.setAmount(invoice.getCost());
        transaction.setCreatedAt(LocalDateTime.now());

        // 20% chance of failure
        if (random.nextInt(100) < 40) {
            transaction.setStatus("FAILED");
            transaction.setFailureReason(getRandomFailureReason());
            invoice.setStatus(InvoiceStatus.DECLINED);
            
            invoiceRepository.save(invoice);
            Transaction saved = transactionRepository.save(transaction);
            notificationService.sendReceipt(invoice.getId(), saved.getStatus());
            
            throw new PaymentFailedException(saved.getFailureReason());
        } else {
            transaction.setStatus("SUCCESS");
            invoice.setStatus(InvoiceStatus.COMPLETED);
        }

        invoiceRepository.save(invoice);
        Transaction saved = transactionRepository.save(transaction);

        notificationService.sendReceipt(invoice.getId(), saved.getStatus());

        return transactionMapper.toDTO(saved);
    }

    public TransactionDTO getTransaction(Long id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException(id));
        return transactionMapper.toDTO(transaction);
    }

    private String getRandomFailureReason() {
        String[] reasons = { "Insufficient funds", "Invalid card", "Card expired", "Suspected fraud" };
        return reasons[random.nextInt(reasons.length)];
    }

    private String maskCardNumber(String cardNumber) {
        if (cardNumber == null || cardNumber.length() < 4) {
            return "****";
        }
        return "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
    }
}
