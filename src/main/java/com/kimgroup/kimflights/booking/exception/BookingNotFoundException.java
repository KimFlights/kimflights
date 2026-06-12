package com.kimgroup.kimflights.booking.exception;

public class BookingNotFoundException extends RuntimeException {

    public BookingNotFoundException(String bookingReference) {
        super("Booking not found: " + bookingReference);
    }
}