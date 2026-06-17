package com.kimgroup.kimflights.flight;

import lombok.Builder;
import jakarta.validation.constraints.NotBlank;

@Builder
public record AirportDTO(
    @NotBlank(message = "Airport code is required")
    String code,

    @NotBlank(message = "Address ID is required")
    String addressId
) {}

