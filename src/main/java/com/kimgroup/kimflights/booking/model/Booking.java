package com.kimgroup.kimflights.booking.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Booking = Aggregate Root
 * Passenger = Child Entity
 * Ticket = Child Entity
 * Luggage = Child Entity
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    
    @Id
    private String bookingReference;
    private LocalDate reservedDate;
    
    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @OneToMany(
        mappedBy = "booking",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Passenger> passengers = new ArrayList<>();

    public BigDecimal getTotalPrice() {
        BigDecimal ticketTotal =
                passengers.stream()
                        .flatMap(p -> p.getTickets().stream())
                        .map(Ticket::getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal luggageTotal =
                passengers.stream()
                        .flatMap(p -> p.getLuggage().stream())
                        .map(Luggage::getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

        return ticketTotal.add(luggageTotal);
    }

    public void addPassenger(Passenger passenger) {
        passengers.add(passenger);
        passenger.setBooking(this);
    }

    public void removePassenger(Passenger passenger) {
        passengers.remove(passenger);
        passenger.setBooking(null);
    }
}

