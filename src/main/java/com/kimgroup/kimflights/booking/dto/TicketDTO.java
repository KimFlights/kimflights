package com.kimgroup.kimflights.booking.dto;

import lombok.Builder;
import java.math.BigDecimal;

import com.kimgroup.kimflights.booking.TicketStatus;

@Builder
public record TicketDTO(
    String ticketCode,
    String type,
    BigDecimal price,
    TicketStatus availability,
    String flightId
) {}
