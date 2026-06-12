package com.kimgroup.kimflights.flight.internal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
class Aircraft {
    @Id
    private String name;
    private String manufacturer;
    private Integer seatCapacity;
}
