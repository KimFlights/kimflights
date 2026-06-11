package com.kimgroup.kimflights.booking.internal;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
class BookingService {
    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<BookingDTO> findAll() {
        return bookingRepository.findAll().stream()
            .map(booking -> new BookingDTO(
                booking.getBookingReference(),
                booking.getReservedDate(),
                booking.getBookingStatus()
            ))
            .toList();
    }
}
