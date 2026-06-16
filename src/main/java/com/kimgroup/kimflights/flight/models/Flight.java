package com.kimgroup.kimflights.flight.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @ManyToOne
    @JoinColumn(name = "aircraft_name")
    private Aircraft aircraft;

    @ManyToOne(optional = false)
    @JoinColumn(name = "flight_code", nullable = false)
    private FlightRoute flightRoute;

    @ManyToOne
    @JoinColumn(name = "origin_override_airport_code")
    private Airport originOverride;

    @ManyToOne
    @JoinColumn(name = "destination_override_airport_code")
    private Airport destinationOverride;

    public Optional<Aircraft> getAircraft() {
        return Optional.ofNullable(aircraft);
    }
}
