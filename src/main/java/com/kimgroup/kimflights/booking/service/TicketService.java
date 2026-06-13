package com.kimgroup.kimflights.booking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kimgroup.kimflights.booking.dto.TicketDTO;
import com.kimgroup.kimflights.booking.exception.BookingNotFoundException;
import com.kimgroup.kimflights.booking.exception.TicketNotFoundException;
import com.kimgroup.kimflights.booking.model.Booking;
import com.kimgroup.kimflights.booking.model.Ticket;
import com.kimgroup.kimflights.booking.repository.BookingRepository;
import com.kimgroup.kimflights.booking.repository.TicketRepository;
import com.kimgroup.kimflights.flight.FlightIdUpdatedEvent;

import org.springframework.modulith.events.ApplicationModuleListener;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final BookingRepository bookingRepository;

    public TicketService(TicketRepository ticketRepository,
        BookingRepository bookingRepository) {
        this.ticketRepository = ticketRepository;
        this.bookingRepository = bookingRepository;
    }

    // ------------------ READ ALL ------------------

    public List<TicketDTO> findAll() {
        return ticketRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    // ------------------ READ BY ID ------------------

    public TicketDTO findById(String ticketCode) {

        Ticket ticket = ticketRepository.findById(ticketCode)
                .orElseThrow(() ->
                        new TicketNotFoundException(ticketCode));

        return mapToDTO(ticket);
    }

    // ------------------ CREATE ------------------

    public TicketDTO createTicket(TicketDTO dto) {

        Booking booking = bookingRepository.findById(dto.bookingReference())
                .orElseThrow(() ->
                        new BookingNotFoundException(dto.bookingReference()));

        Ticket ticket = new Ticket();

        ticket.setTicketCode(dto.ticketCode());
        ticket.setType(dto.type());
        ticket.setPrice(dto.price());
        ticket.setAvailability(dto.availability());
        ticket.setFlightId(dto.flightId());
        ticket.setBooking(booking);

        Ticket saved = ticketRepository.save(ticket);

        return mapToDTO(saved);
    }

    // ------------------ UPDATE ------------------

    public TicketDTO updateTicket(
        String ticketCode,
        TicketDTO dto) {
        Ticket existing = ticketRepository.findById(ticketCode)
                .orElseThrow(() ->
                        new TicketNotFoundException(ticketCode));

        Booking booking = bookingRepository.findById(dto.bookingReference())
                .orElseThrow(() ->
                        new BookingNotFoundException(dto.bookingReference()));

        existing.setType(dto.type());
        existing.setPrice(dto.price());
        existing.setAvailability(dto.availability());
        existing.setFlightId(dto.flightId());
        existing.setBooking(booking);

        Ticket updated = ticketRepository.save(existing);

        return mapToDTO(updated);
    }

    // ------------------ DELETE ------------------

    public void deleteTicket(String ticketCode) {

        if (!ticketRepository.existsById(ticketCode)) {
            throw new TicketNotFoundException(ticketCode);
        }

        ticketRepository.deleteById(ticketCode);
    }

    // ------------------ MAPPER ------------------

    private TicketDTO mapToDTO(Ticket ticket) {
        return TicketDTO.builder()
                .ticketCode(ticket.getTicketCode())
                .type(ticket.getType())
                .price(ticket.getPrice())
                .availability(ticket.getAvailability())
                .flightId(ticket.getFlightId())
                .bookingReference(
                        ticket.getBooking() != null
                                ? ticket.getBooking().getBookingReference()
                                : null
                )
                .build();
    }

    // existing listener
    @ApplicationModuleListener
    public void onFlightIdUpdated(FlightIdUpdatedEvent event) {
        ticketRepository.updateFlightId(event.oldFlightId(), event.newFlightId());
    }
}
