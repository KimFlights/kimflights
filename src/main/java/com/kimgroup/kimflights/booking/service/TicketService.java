package com.kimgroup.kimflights.booking.service;

import com.kimgroup.kimflights.booking.dto.TicketDTO;
import com.kimgroup.kimflights.booking.repository.TicketRepository;
import com.kimgroup.kimflights.flight.FlightIdUpdatedEvent;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<TicketDTO> findAll() {
        return ticketRepository.findAll().stream()
            .map(ticket -> TicketDTO.builder()
                .ticketCode(ticket.getTicketCode())
                .type(ticket.getType())
                .price(ticket.getPrice())
                .availability(ticket.getAvailability())
                .flightId(ticket.getFlightId())
                .build())
            .toList();
    }

    @ApplicationModuleListener
    public void onFlightIdUpdated(FlightIdUpdatedEvent event) {
        ticketRepository.updateFlightId(event.oldFlightId(), event.newFlightId());
    }
}
