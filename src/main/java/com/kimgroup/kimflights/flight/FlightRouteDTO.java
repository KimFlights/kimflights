package com.kimgroup.kimflights.flight;

import lombok.Builder;
import java.time.LocalTime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Builder
public record FlightRouteDTO(
    @NotBlank(message = "Flight code is required")
    String flightCode,

    @NotNull(message = "Departure time is required")
    LocalTime departureTime,

    @NotNull(message = "Arrival time is required")
    LocalTime arrivalTime,

    @NotBlank(message = "Airline name is required")
    String airlineName,

    @NotBlank(message = "Origin airport code is required")
    String originAirportCode,

    @NotBlank(message = "Destination airport code is required")
    String destinationAirportCode
) {}
