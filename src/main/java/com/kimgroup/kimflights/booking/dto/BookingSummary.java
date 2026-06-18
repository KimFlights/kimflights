package com.kimgroup.kimflights.booking.dto;

import java.math.BigDecimal;

public record BookingSummary(
        String bookingReference,
        BigDecimal totalPrice
) {
}