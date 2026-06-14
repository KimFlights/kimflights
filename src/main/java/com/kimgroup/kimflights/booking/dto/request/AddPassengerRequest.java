package com.kimgroup.kimflights.booking.dto.request;

import com.kimgroup.kimflights.booking.dto.AddressDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record AddPassengerRequest(

    @NotBlank(message = "Passenger name is required")
    String name,

    @NotBlank(message = "Passport number is required")
    String passportNumber,

    @Valid
    AddressDTO address

) {}