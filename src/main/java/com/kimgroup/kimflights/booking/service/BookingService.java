package com.kimgroup.kimflights.booking.service;

import com.kimgroup.kimflights.booking.model.BookingStatus;

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

import com.kimgroup.kimflights.booking.dto.*;
import com.kimgroup.kimflights.booking.dto.request.*;
import com.kimgroup.kimflights.booking.dto.response.BookingResponse;

import java.util.List;

public interface BookingService {

    // CREATE
    BookingResponse createBooking(CreateBookingRequest request);

    // READ
    BookingResponse getBooking(String bookingReference);

    List<BookingSummary> getAllBookings();

    // STATUS
    BookingResponse updateBookingStatus(String bookingReference, BookingStatus status);

    void cancelBooking(String bookingReference);

    // PASSENGER OPERATIONS
    PassengerDTO addPassenger(String bookingReference, AddPassengerRequest request);

    void removePassenger(String bookingReference, String passengerId);

    // TICKET OPERATIONS
    TicketDTO addTicket(
            String bookingReference,
            String passengerId,
            AddTicketRequest request
    );

    void removeTicket(
            String bookingReference,
            String passengerId,
            String ticketCode
    );

    // LUGGAGE OPERATIONS
    LuggageDTO addLuggage(
            String bookingReference,
            String passengerId,
            AddLuggageRequest request
    );

    void removeLuggage(
            String bookingReference,
            String passengerId,
            Integer luggageId
    );
}