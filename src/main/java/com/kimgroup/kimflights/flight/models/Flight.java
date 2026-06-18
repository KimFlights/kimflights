package com.kimgroup.kimflights.flight.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.Optional;

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
    
    @Enumerated(EnumType.STRING)
    private FlightStatus flightStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aircraft_name")
    private Aircraft aircraft;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "airline_id", nullable = false)
    private Airline airline;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "origin_airport_code", nullable = false)
    private Airport origin;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "destination_airport_code", nullable = false)
    private Airport destination;

    public Optional<Aircraft> getAircraft() {
        return Optional.ofNullable(aircraft);
    }
}
