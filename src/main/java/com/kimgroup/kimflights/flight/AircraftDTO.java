package com.kimgroup.kimflights.flight;

import lombok.Builder;

/**
 * TODO: Update to record with @Builder
 */
@Builder
public record AircraftDTO(
    String name,
    String manufacturer,
    Integer seatCapacity
) {}
