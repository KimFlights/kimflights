package com.kimgroup.kimflights.booking.service;

import java.math.BigDecimal;

interface PricingService {

    BigDecimal calculateBookingTotal(String bookingReference);

    BigDecimal calculatePassengerTotal(String passengerId);
}