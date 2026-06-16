package com.kimgroup.kimflights.flight.exception;

public class AirlineNotFoundException extends RuntimeException {
    public AirlineNotFoundException(String name) {
        super("Airline not found: " + name);
    }

    public AirlineNotFoundException(Integer id) {
        super("Airline not found with ID: " + id);
    }
}

