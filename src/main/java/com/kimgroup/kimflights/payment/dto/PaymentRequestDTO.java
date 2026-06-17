package com.kimgroup.kimflights.payment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record PaymentRequestDTO(
    @NotBlank(message = "Card number is required")
    String cardNumber,
    
    @NotNull(message = "Invoice ID is required")
    Integer invoiceId
) {}
