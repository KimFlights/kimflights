package com.kimgroup.kimflights.booking.exception;

public class TicketNotFoundException extends RuntimeException {

    public TicketNotFoundException(String ticketCode) {
        super("Ticket not found: " + ticketCode);
    }
}