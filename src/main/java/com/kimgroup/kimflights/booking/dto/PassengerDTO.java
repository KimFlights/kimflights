package com.kimgroup.kimflights.booking.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.List;

@Builder
public record PassengerDTO(
    String id,

    @NotBlank(message = "Passenger name is required")
    String name,

    @NotBlank(message = "Passport number is required")
    String passportNumber,

    @Valid
    AddressDTO address,

    @Valid
    List<TicketDTO> tickets,

    @Valid
    List<LuggageDTO> luggage

) {}