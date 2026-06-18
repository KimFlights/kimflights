package com.kimgroup.kimflights.booking;

import com.kimgroup.kimflights.booking.model.BookingStatus;
import com.kimgroup.kimflights.booking.dto.CreateBookingRequest;

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


/**
 * Requirements:
 * 1. send BookingSummary to payment module
 * 2. allow front end to create a booking from beginning to end, filling out passenger details, ticket details, luggage details & total cost
 * 3. send the information of those summaries to the proper modules, and once validated, store that information in the database
 */

import com.kimgroup.kimflights.booking.dto.*;
import java.util.List;

public interface BookingService {

    // Booking
    BookingResponse createBooking(CreateBookingRequest request);

    BookingResponse getBooking(String bookingReference);

    List<BookingSummary> getAllBookings();

    BookingResponse updateBookingStatus(
            String bookingReference,
            BookingStatus status);

    void cancelBooking(String bookingReference);

    BookingSummary getBookingSummary(String bookingReference);

    BookingResponse findById(String bookingReference);
    
    List<BookingResponse> getUserBookings(String username);
}