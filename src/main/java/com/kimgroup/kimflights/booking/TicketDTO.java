package com.kimgroup.kimflights.booking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDTO {
    private String ticketCode;
    private String type;
    private BigDecimal price;
    private TicketStatus availability;
    private String flightId;
}
