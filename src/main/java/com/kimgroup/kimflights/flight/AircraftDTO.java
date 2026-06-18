package com.kimgroup.kimflights.flight;

import lombok.Builder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

@Builder
public record AircraftDTO(
    @NotBlank(message = "Aircraft name is required")
    String name,

    @NotBlank(message = "Manufacturer is required")
    String manufacturer,

    @NotNull(message = "Seat capacity is required")
    @Positive(message = "Seat capacity must be positive")
    Integer seatCapacity,

    List<AircraftSeatDTO> seats
) {}

