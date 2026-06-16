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

    @NotBlank(message = "Flight code is required")
    String flightCode,

    String aircraftName,

    String airlineName,

    String originAirportCode,

    String destinationAirportCode,

    String originAirportCodeOverride,

    String destinationAirportCodeOverride
) {}
