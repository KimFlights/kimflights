package com.kimgroup.kimflights.booking;

import lombok.Builder;
import java.time.LocalDate;

@Builder
public record BookingDTO(
    String bookingReference,
    LocalDate reservedDate,
    Boolean bookingStatus
) {}
