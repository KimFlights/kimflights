package com.kimgroup.kimflights.flight.internal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
    @Id
    private String id;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private Integer distance;
    private Integer estimatedTimeInMinutes;
}
