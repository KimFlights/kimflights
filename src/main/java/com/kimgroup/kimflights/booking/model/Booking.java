package com.kimgroup.kimflights.booking.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    private String bookingReference;
    private LocalDate reservedDate;
    private Boolean bookingStatus;
    @OneToMany(mappedBy = "booking")
    private List<Passenger> passengers;

    @OneToMany(
        mappedBy = "booking",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Ticket> tickets;
    
    public BigDecimal getTotalPrice() {
        if (tickets == null || tickets.isEmpty()) {
            return BigDecimal.ZERO;
        }
        return tickets.stream()
                .map(Ticket::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

