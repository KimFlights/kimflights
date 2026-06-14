package com.kimgroup.kimflights.booking.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String passportNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_reference")
    private Booking booking;

    @Embedded
    private Address address;

    @OneToMany(
        mappedBy = "passenger",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Luggage> luggage = new ArrayList<>();

    @OneToMany(
        mappedBy = "passenger",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Ticket> tickets = new ArrayList<>();

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
        ticket.setPassenger(this);
    }

    public void removeTicket(Ticket ticket) {
        tickets.remove(ticket);
        ticket.setPassenger(null);
    }
    
    public void addLuggage(Luggage luggage) {
        this.luggage.add(luggage);
        luggage.setPassenger(this);
    }
    
}