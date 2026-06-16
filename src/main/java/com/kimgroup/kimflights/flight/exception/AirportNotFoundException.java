package com.kimgroup.kimflights.flight.exception;

public class AirportNotFoundException extends RuntimeException {
    public AirportNotFoundException(String code) {
        super("Airport not found: " + code);
    }
}
