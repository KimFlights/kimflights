package com.kimgroup.kimflights.booking.internal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
class Booking {
    @Id
    private String bookingReference;
    private LocalDate reservedDate;
    private Boolean bookingStatus;
}
