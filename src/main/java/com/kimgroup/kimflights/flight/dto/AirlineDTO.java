package com.kimgroup.kimflights.flight.dto;

import lombok.Builder;

@Builder
public record AirlineDTO(
    Integer id,
    String code,
    String name
) {}
