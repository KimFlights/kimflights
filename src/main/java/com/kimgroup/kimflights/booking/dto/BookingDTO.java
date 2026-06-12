package com.kimgroup.kimflights.booking.dto;

import lombok.Builder;
import java.time.LocalDate;

@Builder
public record BookingDTO(
    String bookingReference,
    LocalDate reservedDate,
    Boolean bookingStatus
) {}
