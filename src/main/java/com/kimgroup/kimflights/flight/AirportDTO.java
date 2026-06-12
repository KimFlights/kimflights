package com.kimgroup.kimflights.flight;

import lombok.Builder;

@Builder
public record AirportDTO(
    String code,
    String addressId
) {}
