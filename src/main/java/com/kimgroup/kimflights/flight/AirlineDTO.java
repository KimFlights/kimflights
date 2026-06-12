package com.kimgroup.kimflights.flight;

import lombok.Builder;

@Builder
public record AirlineDTO(
    Integer id,
    String code,
    String name
) {}
