package com.kimgroup.kimflights.payment;

import lombok.Builder;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record InvoiceDTO(
    Integer id,
    LocalDateTime createdAt,
    BigDecimal cost,
    String paymentMethod,
    InvoiceStatus status
) {}
