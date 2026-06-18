package com.kimgroup.kimflights.flight.exception;

public class AircraftSeatNotFoundException extends RuntimeException {
    public AircraftSeatNotFoundException(String id) {
        super("AircraftSeat not found: " + id);
    }
}
