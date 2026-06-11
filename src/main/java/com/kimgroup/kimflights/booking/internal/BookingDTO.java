package com.kimgroup.kimflights.booking.internal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
class BookingDTO {
    private String bookingReference;
    private LocalDate reservedDate;
    private Boolean bookingStatus;
}
