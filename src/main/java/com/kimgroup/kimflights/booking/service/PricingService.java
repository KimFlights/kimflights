package com.kimgroup.kimflights.booking.service;

import java.math.BigDecimal;

public interface PricingService {
    BigDecimal calculateBookingTotal(String bookingReference);
}