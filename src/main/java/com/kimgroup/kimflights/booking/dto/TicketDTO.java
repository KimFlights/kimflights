package com.kimgroup.kimflights.booking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

import com.kimgroup.kimflights.booking.TicketStatus;

@Builder
public record TicketDTO(

    @NotBlank(message = "Ticket code is required")
    String ticketCode,

    @NotBlank(message = "Ticket type is required")
    String type,

    @NotNull(message = "Price is required")
    BigDecimal price,

    @NotNull(message = "Availability is required")
    TicketStatus availability,

    @NotBlank(message = "Flight ID is required")
    String flightId

) {}