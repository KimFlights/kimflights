package com.kimgroup.kimflights.flight;

import lombok.Builder;
import jakarta.validation.constraints.NotBlank;

@Builder
public record AircraftSeatDTO(
    @NotBlank(message = "ID is required")
    String id,

    @NotBlank(message = "Aircraft ID is required")
    String aircraftId,

    @NotBlank(message = "Seat number is required")
    String seatNumber,

    @NotBlank(message = "Cabin is required")
    String cabin
) {}
