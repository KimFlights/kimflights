package com.kimgroup.kimflights.payment.dto;

import lombok.Builder;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.kimgroup.kimflights.payment.models.InvoiceStatus;

@Builder
public record InvoiceDTO(
    Integer id,
    LocalDateTime createdAt,
    BigDecimal cost,
    String paymentMethod,
    InvoiceStatus status
) {}
