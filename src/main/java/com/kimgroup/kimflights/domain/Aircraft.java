package com.kimgroup.kimflights.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Aircraft {
    private String name;
    private String manufacturer;
    private Integer seatCapacity;
}
