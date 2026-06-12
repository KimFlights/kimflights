package com.kimgroup.kimflights.flight.dto;

import lombok.Builder;

@Builder
public record AirportDTO(
    String code,
    String addressId
) {}
