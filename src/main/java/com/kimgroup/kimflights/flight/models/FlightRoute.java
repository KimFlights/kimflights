package com.kimgroup.kimflights.flight.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightRoute {
    @Id
    private String flightCode; // e.g., "AA100"

    private LocalTime departureTime;
    private LocalTime arrivalTime;

    @ManyToOne(optional = false)
    @JoinColumn(name = "airline_id", nullable = false)
    private Airline airline;

    @ManyToOne(optional = false)
    @JoinColumn(name = "origin_airport_code", nullable = false)
    private Airport origin;

    @ManyToOne(optional = false)
    @JoinColumn(name = "destination_airport_code", nullable = false)
    private Airport destination;
}
