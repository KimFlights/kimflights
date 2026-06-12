package com.kimgroup.kimflights.flight;

import lombok.Builder;

@Builder
public record AirportDTO(
    Integer id,
    String code,
    String name
) {}
