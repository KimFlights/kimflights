package com.kimgroup.kimflights.flight;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO: Update to record with @Builder
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AircraftDTO {
    private String name;
    private String manufacturer;
    private Integer seatCapacity;
}
