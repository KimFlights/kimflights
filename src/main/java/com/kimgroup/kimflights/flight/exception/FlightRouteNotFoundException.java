package com.kimgroup.kimflights.flight.exception;

public class FlightRouteNotFoundException extends RuntimeException {
    public FlightRouteNotFoundException(String flightCode) {
        super("Flight route not found: " + flightCode);
    }
}
