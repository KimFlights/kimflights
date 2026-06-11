package com.kimgroup.kimflights.flight;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightDTO {
    private String id;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private Integer distance;
    private Integer estimatedTimeInMinutes;
}
