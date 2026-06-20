package com.kimgroup.kimflights.booking.service;

import com.kimgroup.kimflights.booking.TicketStatus;
import com.kimgroup.kimflights.booking.model.*;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BookingDomainService {

    public Booking createNewBooking(String reference) {

        Booking booking = new Booking();
        booking.setBookingReference(reference);
        booking.setReservedDate(LocalDate.now());
        booking.setStatus(BookingStatus.PENDING_PAYMENT);

        return booking;
    }

    public Passenger createPassenger(String name, String passport, Address address) {

        Passenger passenger = new Passenger();
        passenger.setName(name);
        passenger.setPassportNumber(passport);
        passenger.setAddress(address);

        return passenger;
    }

    public Ticket createTicket(String code, String type, java.math.BigDecimal price,
                               TicketStatus status, String flightId, String seatNum) {

        Ticket ticket = new Ticket();
        ticket.setTicketCode(code);
        ticket.setType(type);
        ticket.setPrice(price);
        ticket.setAvailability(status);
        ticket.setFlightId(flightId);
        ticket.setSeatNum(seatNum);

        return ticket;
    }

    public Luggage createLuggage(java.math.BigDecimal weight, String type,
                                 java.math.BigDecimal price) {

        Luggage luggage = new Luggage();
        luggage.setWeight(weight);
        luggage.setType(type);
        luggage.setPrice(price);

        return luggage;
    }
}