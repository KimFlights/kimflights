package com.kimgroup.kimflights.booking;

import com.kimgroup.kimflights.booking.dto.CreateBookingRequest;
import com.kimgroup.kimflights.booking.dto.*;
import com.kimgroup.kimflights.booking.exception.BookingNotFoundException;
import com.kimgroup.kimflights.booking.model.*;
import com.kimgroup.kimflights.booking.repository.BookingRepository;
import com.kimgroup.kimflights.booking.service.*;
import com.kimgroup.kimflights.booking.util.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final BookingReferenceGenerator referenceGenerator;

    private final BookingValidationService validationService;
    private final BookingDomainService domainService;

    private final PassengerMapper passengerMapper;

    // ---------------- CREATE ----------------

    @Override
    public BookingResponse createBooking(CreateBookingRequest request) {

        String reference;
        do {
            reference = referenceGenerator.generate();
        } while (bookingRepository.existsById(reference));

        Booking booking = domainService.createNewBooking(reference);

        for (PassengerDTO p : request.passengers()) {

            Passenger passenger = domainService.createPassenger(
                    p.name(),
                    p.passportNumber(),
                    toAddress(p.address())
            );

            booking.addPassenger(passenger);

            if (p.tickets() != null) {
                for (TicketDTO t : p.tickets()) {
                    Ticket ticket = domainService.createTicket(
                            t.ticketCode(),
                            t.type(),
                            t.price(),
                            t.availability(),
                            t.flightId()
                    );

                    passenger.addTicket(ticket);
                }
            }

            if (p.luggage() != null) {
                for (LuggageDTO l : p.luggage()) {
                    Luggage luggage = domainService.createLuggage(
                            l.weight(),
                            l.type(),
                            l.price()
                    );

                    passenger.addLuggage(luggage);
                }
            }
        }

        bookingRepository.save(booking);
        return toResponse(booking);
    }

    // ---------------- READ ----------------

    @Override
    public BookingResponse getBooking(String bookingReference) {

        validationService.validateBookingExists(bookingReference);

        Booking booking = bookingRepository.findById(bookingReference)
                .orElseThrow(() -> new BookingNotFoundException(bookingReference));

        return toResponse(booking);
    }

    @Override
    public List<BookingSummary> getAllBookings() {

        return bookingRepository.findAll()
                .stream()
                .map(b -> new BookingSummary(
                        b.getBookingReference(),
                        b.getTotalPrice()
                ))
                .toList();
    }

    // ---------------- STATUS ----------------

    @Override
    public BookingResponse updateBookingStatus(String bookingReference, BookingStatus status) {

        Booking booking = bookingRepository.findById(bookingReference)
                .orElseThrow(() -> new BookingNotFoundException(bookingReference));

        booking.setStatus(status);

        return toResponse(booking);
    }

    @Override
    public void cancelBooking(String bookingReference) {

        validationService.validateBookingExists(bookingReference);

        Booking booking = bookingRepository.findById(bookingReference)
                .orElseThrow(() -> new BookingNotFoundException(bookingReference));

        booking.setStatus(BookingStatus.CANCELLED);
    }
    
    @Override
    @Transactional(readOnly = true)
    public BookingSummary getBookingSummary(String bookingReference) {

        Booking booking = bookingRepository.findById(bookingReference)
                .orElseThrow(() -> new BookingNotFoundException(bookingReference));

        return new BookingSummary(
                booking.getBookingReference(),
                booking.getTotalPrice()
        );
    }


    @Override
    @Transactional(readOnly = true)
    public BookingResponse findById(String bookingReference) {
        
        validationService.validateBookingExists(bookingReference);

        Booking booking = bookingRepository.findById(bookingReference)
                .orElseThrow(() -> new BookingNotFoundException(bookingReference));

        return toResponse(booking);
    }
    
    // ---------------- HELPERS ----------------

    private Address toAddress(AddressDTO dto) {
        if (dto == null) return null;

        return new Address(
                dto.street(),
                dto.city(),
                dto.state(),
                dto.country(),
                dto.postalCode()
        );
    }

    private BookingResponse toResponse(Booking booking) {
        return BookingResponse.builder()
                .bookingReference(booking.getBookingReference())
                .reservedDate(booking.getReservedDate())
                .status(booking.getStatus())
                .totalPrice(booking.getTotalPrice())
                .passengers(
                        booking.getPassengers()
                                .stream()
                                .map(passengerMapper::toDTO)
                                .toList()
                )
                .build();
    }

    
}