package com.kimgroup.kimflights.booking.service;

import com.kimgroup.kimflights.booking.dto.BookingDTO;
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
    
}
