package com.kimgroup.kimflights.booking;

import lombok.Builder;
import java.math.BigDecimal;

@Builder
public record TicketDTO(
    String ticketCode,
    String type,
    BigDecimal price,
    TicketStatus availability,
    String flightId
) {}
