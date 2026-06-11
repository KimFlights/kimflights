package com.kimgroup.kimflights.booking;

import lombok.Builder;
import java.time.LocalDate;

/**
 * TODO: Update to record with @Builder
 */
@Builder
public record BookingDTO(
    String bookingReference,
    LocalDate reservedDate,
    Boolean bookingStatus
) {}
