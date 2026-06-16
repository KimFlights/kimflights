package com.kimgroup.kimflights.flight;

import lombok.Builder;
import java.time.LocalDateTime;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import com.kimgroup.kimflights.flight.models.FlightStatus;

@Builder
public record FlightDTO(
    @NotBlank(message = "Flight ID is required")
    String id,

    @NotNull(message = "Departure date is required")
    LocalDateTime departureDate,

    @NotNull(message = "Arrival date is required")
    LocalDateTime arrivalDate,

    @NotNull(message = "Distance is required")
    @Positive(message = "Distance must be positive")
    Integer distance,

    @NotNull(message = "Estimated time in minutes is required")
    @Positive(message = "Estimated time must be positive")
    Integer estimatedTimeInMinutes,

    @NotNull(message = "Flight status is required")
    FlightStatus flightStatus,

    String aircraftName,

    @NotBlank(message = "Airline name is required")
    String airlineName,

    @NotBlank(message = "Origin airport code is required")
    String originAirportCode,

    @NotBlank(message = "Destination airport code is required")
    String destinationAirportCode
) {}

