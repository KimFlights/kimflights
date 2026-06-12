package com.kimgroup.kimflights.booking.model;

import com.kimgroup.kimflights.booking.TicketStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {
    @Id
    private String ticketCode;
    private String type;
    private BigDecimal price;
    
    @Enumerated(EnumType.STRING)
    private TicketStatus availability;
    private String flightId;
}
