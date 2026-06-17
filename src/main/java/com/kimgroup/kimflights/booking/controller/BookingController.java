package com.kimgroup.kimflights.booking.controller;


import com.kimgroup.kimflights.booking.BookingService;
import com.kimgroup.kimflights.booking.dto.BookingSummary;
import com.kimgroup.kimflights.booking.dto.request.CreateBookingRequest;
import com.kimgroup.kimflights.booking.dto.response.BookingResponse;
import com.kimgroup.kimflights.booking.model.BookingStatus;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    
    @PostConstruct
    public void init() {
        System.out.println("BookingController loaded!");
    }

    // ---------------- CREATE ----------------

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(
            @Valid @RequestBody CreateBookingRequest request
    ) {
        return ResponseEntity.ok(bookingService.createBooking(request));
    }

    // ---------------- READ ----------------

    @GetMapping("/{bookingReference}")
    public ResponseEntity<BookingResponse> getBooking(
            @PathVariable String bookingReference
    ) {
        return ResponseEntity.ok(bookingService.getBooking(bookingReference));
    }

    @GetMapping
    public ResponseEntity<List<BookingSummary>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @GetMapping("/{bookingReference}/summary")
    public ResponseEntity<BookingSummary> getBookingSummary(
            @PathVariable String bookingReference
    ) {
        return ResponseEntity.ok(
                bookingService.getBookingSummary(bookingReference)
        );
    }

    // ---------------- STATUS ----------------

    @PatchMapping("/{bookingReference}/status")
    public ResponseEntity<BookingResponse> updateStatus(
            @PathVariable String bookingReference,
            @RequestParam BookingStatus status
    ) {
        return ResponseEntity.ok(
                bookingService.updateBookingStatus(bookingReference, status)
        );
    }
}