package com.kimgroup.kimflights.booking.controller;

import com.kimgroup.kimflights.booking.dto.BookingDTO;
import com.kimgroup.kimflights.booking.service.BookingService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public List<BookingDTO> findAll() {
        return bookingService.findAll();
    }

    // GET by id
    @GetMapping("/{id}")
    public BookingDTO findById(@PathVariable String id) {
        return bookingService.findById(id);
    }

    // CREATE
    @PostMapping
    public BookingDTO create(@Valid @RequestBody BookingDTO dto) {
        return bookingService.createBooking(dto);
    }

    // UPDATE
    @PutMapping("/{id}")
    public BookingDTO update(
            @PathVariable String id,
            @Valid @RequestBody BookingDTO dto
    ) {
        return bookingService.updateBooking(id, dto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        bookingService.deleteBooking(id);
    }
}
