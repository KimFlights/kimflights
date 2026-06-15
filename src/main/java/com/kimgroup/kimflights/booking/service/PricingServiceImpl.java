package com.kimgroup.kimflights.booking.service;

import com.kimgroup.kimflights.booking.exception.BookingNotFoundException;
import com.kimgroup.kimflights.booking.model.Booking;
import com.kimgroup.kimflights.booking.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PricingServiceImpl implements PricingService {

    private final BookingRepository bookingRepository;

    @Override
    public BigDecimal calculateBookingTotal(String bookingReference) {

        Booking booking = bookingRepository.findById(bookingReference)
                .orElseThrow(() -> new BookingNotFoundException(bookingReference));

        return booking.getTotalPrice();
    }
}