package com.kimgroup.kimflights.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AircraftDTO {
    private String name;
    private String manufacturer;
    private Integer seatCapacity;
}
