package com.kimgroup.kimflights.flight;

import lombok.Builder;
import jakarta.validation.constraints.NotBlank;

@Builder
public record AirlineDTO(
    Integer id,

    @NotBlank(message = "Airline code is required")
    String code,

    @NotBlank(message = "Airline name is required")
    String name
) {}

