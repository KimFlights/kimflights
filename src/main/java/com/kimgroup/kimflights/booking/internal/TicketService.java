package com.kimgroup.kimflights.booking.internal;

import com.kimgroup.kimflights.booking.TicketDTO;
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
            .map(ticket -> new TicketDTO(
                ticket.getTicketCode(),
                ticket.getType(),
                ticket.getPrice(),
                ticket.getAvailability(),
                ticket.getFlightId()
            ))
            .toList();
    }

    @ApplicationModuleListener
    public void onFlightIdUpdated(FlightIdUpdatedEvent event) {
        ticketRepository.updateFlightId(event.oldFlightId(), event.newFlightId());
    }
}
