package com.kimgroup.kimflights.booking.exception;

public class AddressNotFoundException extends RuntimeException {

    public AddressNotFoundException(String id) {
        super("Address not found: " + id);
    }
}