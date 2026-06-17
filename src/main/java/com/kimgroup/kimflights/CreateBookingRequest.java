package com.kimgroup.kimflights;

import com.kimgroup.kimflights.booking.dto.PassengerDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.util.List;

@Builder
public record CreateBookingRequest(

    @Valid
    @NotEmpty(message = "At least one passenger is required")
    List<PassengerDTO> passengers

) {}