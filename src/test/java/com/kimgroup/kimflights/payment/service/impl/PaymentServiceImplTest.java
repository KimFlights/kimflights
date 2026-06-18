package com.kimgroup.kimflights.payment.service.impl;

import com.kimgroup.kimflights.notification.NotificationService;
import com.kimgroup.kimflights.payment.dto.PaymentRequestDTO;
import com.kimgroup.kimflights.payment.dto.TransactionDTO;
import com.kimgroup.kimflights.payment.mapper.TransactionMapper;
import com.kimgroup.kimflights.payment.model.Invoice;
import com.kimgroup.kimflights.payment.model.Transaction;
import com.kimgroup.kimflights.payment.repository.InvoiceRepository;
import com.kimgroup.kimflights.payment.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private TransactionMapper transactionMapper;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Test
    void shouldCheckCardBrand() {
        assertEquals("VISA", paymentService.getCardBrand("411111"));
        assertEquals("MASTERCARD", paymentService.getCardBrand("511111"));
        assertEquals("UNKNOWN", paymentService.getCardBrand(""));
    }

    @Test
    void shouldProcessPayment() {
        PaymentRequestDTO request = PaymentRequestDTO.builder()
                .cardNumber("4111111111111111")
                .invoiceId(1)
                .build();

        Invoice invoice = new Invoice();
        invoice.setId(1);
        invoice.setCost(BigDecimal.valueOf(100.0));

        Transaction savedTransaction = new Transaction();
        savedTransaction.setStatus("SUCCESS");

        TransactionDTO dto = TransactionDTO.builder()
                .id(1L)
                .status("SUCCESS")
                .build();

        when(invoiceRepository.findById(1)).thenReturn(Optional.of(invoice));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(savedTransaction);
        when(transactionMapper.toDTO(any(Transaction.class))).thenReturn(dto);

        TransactionDTO result = paymentService.processPayment(request);

        assertNotNull(result);
        assertEquals("SUCCESS", result.status());

        verify(invoiceRepository).save(invoice);
        verify(transactionRepository).save(any(Transaction.class));
        verify(notificationService).sendReceipt(eq(1), anyString());
    }
}
