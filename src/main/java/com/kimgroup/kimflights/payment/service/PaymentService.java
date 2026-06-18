package com.kimgroup.kimflights.payment.service;

import com.kimgroup.kimflights.payment.dto.PaymentRequestDTO;
import com.kimgroup.kimflights.payment.dto.TransactionDTO;

public interface PaymentService {
    String checkCardBrand(String bin);
    TransactionDTO processPayment(PaymentRequestDTO request);
    TransactionDTO getTransaction(Long id);
}
