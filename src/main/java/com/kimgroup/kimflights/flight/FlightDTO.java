package com.kimgroup.kimflights.flight;

import lombok.Builder;
import java.time.LocalDateTime;

@Builder
public record FlightDTO(
    String id,
    LocalDateTime departureDate,
    LocalDateTime arrivalDate,
    Integer distance,
    Integer estimatedTimeInMinutes
) {}
