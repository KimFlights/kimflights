package com.kimgroup.kimflights.flight.internal;

import com.kimgroup.kimflights.flight.FlightStatus;
import jakarta.persistence.*;
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

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "airline_id", nullable = false)
    private Airline airline;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "origin_airport_code", nullable = false)
    private Airport origin;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_airport_code", nullable = false)
    private Airport destination;

    public Optional<Aircraft> getAircraft() {
        return Optional.ofNullable(aircraft);
    }
}
