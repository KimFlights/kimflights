package com.kimgroup.kimflights.booking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record LuggageDTO(
    Integer id,
    
    @NotNull(message = "Weight is required")
    BigDecimal weight,

    @NotBlank(message = "Luggage type is required")
    String type,

    @NotNull(message = "Price is required")
    BigDecimal price

) {}