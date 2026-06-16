package com.kimgroup.kimflights.flight.exception;

public class FlightNotFoundException extends RuntimeException {
    public FlightNotFoundException(String id) {
        super("Flight not found: " + id);
    }
}
