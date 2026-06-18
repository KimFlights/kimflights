package com.kimgroup.kimflights.booking.exception;

public class LuggageNotFoundException extends RuntimeException {

    public LuggageNotFoundException(Integer id) {
        super("Luggage not found: " + id);
    }
}