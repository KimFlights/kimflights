package com.kimgroup.kimflights.flight;

import lombok.Builder;

@Builder
public record AircraftDTO(
    String name,
    String manufacturer,
    Integer seatCapacity
) {}
