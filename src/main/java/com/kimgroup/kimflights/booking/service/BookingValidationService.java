package com.kimgroup.kimflights.booking.service;

import com.kimgroup.kimflights.booking.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingValidationService {

    private final BookingRepository bookingRepository;

    public void validateBookingExists(String bookingReference) {
        if (!bookingRepository.existsById(bookingReference)) {
            throw new IllegalArgumentException("Booking not found: " + bookingReference);
        }
    }

    public void validateBookingNotCancelled(String bookingReference) {
        bookingRepository.findById(bookingReference).ifPresent(b -> {
            if (b.getStatus().name().equals("CANCELLED")) {
                throw new IllegalStateException("Booking is cancelled");
            }
        });
    }
}