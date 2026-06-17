package com.kimgroup.kimflights.booking.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.util.List;

import com.kimgroup.kimflights.booking.dto.PassengerDTO;

@Builder
public record CreateBookingRequest(

    @Valid
    @NotEmpty(message = "At least one passenger is required")
    List<PassengerDTO> passengers

) {}