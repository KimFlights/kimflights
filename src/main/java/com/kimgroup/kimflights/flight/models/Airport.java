package com.kimgroup.kimflights.flight.models;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Airport {
    @Id
    private String code;

    private String name;   // display name, e.g. "Heathrow", "O'Hare Intl"
    private String region; // state or region, e.g. "California", "Île-de-France"

    @Embedded
    private AirportLocation location; // city and country embedded columns
}
