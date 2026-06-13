package com.kimgroup.kimflights.booking.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record PassengerDTO(

    @NotBlank(message = "Passenger id is required")
    String id,

    @NotBlank(message = "Passenger name is required")
    String name,

    @NotBlank(message = "Passport number is required")
    String passportNumber

) {}