package com.kimgroup.kimflights.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

/**
 * TODO: Update to record with @Builder
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    private String bookingReference;
    private LocalDate reservedDate;
    private Boolean bookingStatus;
}
