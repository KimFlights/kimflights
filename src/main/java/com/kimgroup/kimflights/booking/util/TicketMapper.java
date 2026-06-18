package com.kimgroup.kimflights.booking.util;

import com.kimgroup.kimflights.booking.dto.TicketDTO;
import com.kimgroup.kimflights.booking.model.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {

    public TicketDTO toDTO(Ticket ticket) {

        if (ticket == null) return null;

        return TicketDTO.builder()
                .ticketCode(ticket.getTicketCode())
                .type(ticket.getType())
                .price(ticket.getPrice())
                .availability(ticket.getAvailability())
                .flightId(ticket.getFlightId())
                .build();
    }

    public Ticket toEntity(TicketDTO dto) {

        if (dto == null) return null;

        Ticket ticket = new Ticket();

        ticket.setTicketCode(dto.ticketCode());
        ticket.setType(dto.type());
        ticket.setPrice(dto.price());
        ticket.setAvailability(dto.availability());
        ticket.setFlightId(dto.flightId());

        return ticket;
    }
}