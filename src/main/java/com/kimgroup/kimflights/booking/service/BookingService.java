package com.kimgroup.kimflights.booking.service;

import com.kimgroup.kimflights.booking.model.BookingStatus;
import com.kimgroup.kimflights.booking.dto.BookingDTO;
import com.kimgroup.kimflights.booking.model.Booking;
import com.kimgroup.kimflights.booking.model.Luggage;
import com.kimgroup.kimflights.booking.model.Passenger;
import com.kimgroup.kimflights.booking.model.Ticket;
/*
booking
│
├── model
│   ├── Booking
│   ├── BookingStatus
│   ├── Passenger
│   ├── Ticket
│   ├── TicketStatus
│   ├── Luggage
│   └── Address
│
├── repository
│   ├── BookingRepository
│   ├── PassengerRepository
│   └── TicketRepository
│
├── service
│   ├── BookingService       (public API)
│   ├── PassengerManager     (package-private)
│   ├── TicketManager        (package-private)
│   ├── PricingService       (package-private)
│   └── ValidationService    (package-private)
│
└── web
    └── BookingController
 */

import java.util.List;

public interface BookingService {

    Booking createBooking(Booking booking);

    Booking getBooking(String bookingReference);

    List<Booking> getAllBookings();

    Booking updateBookingStatus(
            String bookingReference,
            BookingStatus status
    );

    void cancelBooking(String bookingReference);

    Passenger addPassenger(
            String bookingReference,
            Passenger passenger
    );

    void removePassenger(
            String bookingReference,
            String passengerId
    );

    Ticket addTicket(
            String bookingReference,
            String passengerId,
            Ticket ticket
    );

    void removeTicket(
            String bookingReference,
            String passengerId,
            String ticketCode
    );

    Luggage addLuggage(
            String bookingReference,
            String passengerId,
            Luggage luggage
    );

    void removeLuggage(
            String bookingReference,
            String passengerId,
            Integer luggageId
    );


    BookingDTO findByBookingReference(String bookingReference);
}