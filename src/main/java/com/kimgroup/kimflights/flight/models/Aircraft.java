package com.kimgroup.kimflights.flight.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Aircraft {
    @Id
    private String name;
    private String manufacturer;
    private Integer seatCapacity;

    @OneToMany(
        mappedBy = "aircraft",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<AircraftSeat> seats = new ArrayList<>();

    public void addSeat(AircraftSeat seat) {
        seats.add(seat);
        seat.setAircraft(this);
    }

    public void removeSeat(AircraftSeat seat) {
        seats.remove(seat);
        seat.setAircraft(null);
    }
}
