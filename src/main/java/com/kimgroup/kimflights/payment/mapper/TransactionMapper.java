package com.kimgroup.kimflights.payment.mapper;

import com.kimgroup.kimflights.payment.dto.TransactionDTO;
import com.kimgroup.kimflights.payment.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    public TransactionDTO toDTO(Transaction transaction) {
        if (transaction == null) {
            return null;
        }
        return TransactionDTO.builder()
                .id(transaction.getId())
                .cardNumber(transaction.getCardNumber())
                .brand(transaction.getBrand())
                .amount(transaction.getAmount())
                .status(transaction.getStatus())
                .failureReason(transaction.getFailureReason())
                .createdAt(transaction.getCreatedAt())
                .build();
    }
}
