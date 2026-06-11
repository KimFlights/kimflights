package com.kimgroup.kimflights.flight.internal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
class AircraftDTO {
    private String name;
    private String manufacturer;
    private Integer seatCapacity;
}
