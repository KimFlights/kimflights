package com.kimgroup.kimflights.flight.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Value object representing the physical location of an airport.
 * Kept inside the flight module to respect modular monolith boundaries —
 * the booking module's Address @Embeddable must NOT be reused here.
 */
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AirportLocation {
    private String city;
    private String country;
    private String state; // state or region, e.g. "California", "Île-de-France"
}
