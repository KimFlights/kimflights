package com.kimgroup.kimflights.booking.dto;

import lombok.Builder;
import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

@Builder
public record BookingDTO(
    @NotBlank(message = "Booking reference is required")
    String bookingReference,
    @NotNull(message = "Reserved date is required")
    LocalDate reservedDate,
    @NotNull(message = "Booking status is required")
    Boolean bookingStatus
) {}
