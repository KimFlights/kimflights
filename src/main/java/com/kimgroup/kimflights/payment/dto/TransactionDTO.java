package com.kimgroup.kimflights.payment.dto;

import lombok.Builder;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record TransactionDTO(
    Long id,
    String cardNumber,
    String brand,
    BigDecimal amount,
    String status,
    String failureReason,
    LocalDateTime createdAt
) {}
