package com.kimgroup.kimflights.flight.exception;

public class AircraftNotFoundException extends RuntimeException {
    public AircraftNotFoundException(String name) {
        super("Aircraft not found: " + name);
    }
}
