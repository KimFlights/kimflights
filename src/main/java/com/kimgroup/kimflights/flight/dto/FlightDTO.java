package com.kimgroup.kimflights.flight.dto;

import lombok.Builder;
import java.time.LocalDateTime;

import com.kimgroup.kimflights.flight.models.FlightStatus;

@Builder
public record FlightDTO(
    String id,
    LocalDateTime departureDate,
    LocalDateTime arrivalDate,
    Integer distance,
    Integer estimatedTimeInMinutes,
    FlightStatus flightStatus,
    String aircraftName,
    String airlineName,
    String originAirportCode,
    String destinationAirportCode
) {}
