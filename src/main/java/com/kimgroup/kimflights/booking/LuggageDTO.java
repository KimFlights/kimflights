package com.kimgroup.kimflights.booking;

import lombok.Builder;
import java.math.BigDecimal;

@Builder
public record LuggageDTO(
    Integer id,
    BigDecimal weight,
    String type,
    double price
) {}
