package com.kimgroup.kimflights.flight;

public record FlightIdUpdatedEvent(String oldFlightId, String newFlightId) {
}
