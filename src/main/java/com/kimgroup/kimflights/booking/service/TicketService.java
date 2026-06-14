package com.kimgroup.kimflights.booking.service;

import com.kimgroup.kimflights.booking.model.Ticket;
import com.kimgroup.kimflights.booking.dto.request.AddTicketRequest;

interface TicketService {

    Ticket addTicketToPassenger(
            String bookingReference,
            String passengerId,
            AddTicketRequest request
    );

    void removeTicket(
            String passengerId,
            String ticketCode
    );

    Ticket getTicket(String ticketCode);
}