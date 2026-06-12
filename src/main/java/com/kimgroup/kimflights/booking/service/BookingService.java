package com.kimgroup.kimflights.booking.service;

import com.kimgroup.kimflights.booking.dto.BookingDTO;
import com.kimgroup.kimflights.booking.model.Booking;
import com.kimgroup.kimflights.booking.repository.BookingRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<BookingDTO> findAll() {
        return bookingRepository.findAll().stream()
            .map(booking -> BookingDTO.builder()
                .bookingReference(booking.getBookingReference())
                .reservedDate(booking.getReservedDate())
                .bookingStatus(booking.getBookingStatus())
                .build())
            .toList();
    }

    //add crud operations
    // ------------------ READ BY ID ------------------
    public BookingDTO findById(String id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found: " + id));

        return mapToDTO(booking);
    }

    // ------------------ CREATE ------------------
    public BookingDTO createBooking(BookingDTO dto) {
        Booking booking = new Booking(
                dto.bookingReference(),
                dto.reservedDate(),
                dto.bookingStatus()
        );

        Booking saved = bookingRepository.save(booking);
        return mapToDTO(saved);
    }

    // ------------------ UPDATE ------------------
    public BookingDTO updateBooking(String id, BookingDTO dto) {
        Booking existing = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found: " + id));

        existing.setReservedDate(dto.reservedDate());
        existing.setBookingStatus(dto.bookingStatus());

        Booking updated = bookingRepository.save(existing);
        return mapToDTO(updated);
    }

    // ------------------ DELETE ------------------
    public void deleteBooking(String id) {
        if (!bookingRepository.existsById(id)) {
            throw new RuntimeException("Booking not found: " + id);
        }
        bookingRepository.deleteById(id);
    }

    // ------------------ MAPPER ------------------
    private BookingDTO mapToDTO(Booking booking) {
        return BookingDTO.builder()
                .bookingReference(booking.getBookingReference())
                .reservedDate(booking.getReservedDate())
                .bookingStatus(booking.getBookingStatus())
                .build();
    }

}
