package com.kimgroup.kimflights.booking.internal;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.kimgroup.kimflights.booking.dto.BookingDTO;
import com.kimgroup.kimflights.booking.exception.BookingNotFoundException;
import com.kimgroup.kimflights.booking.model.Booking;
import com.kimgroup.kimflights.booking.repository.BookingRepository;
import com.kimgroup.kimflights.booking.service.BookingService;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingService bookingService;

    private Booking booking;
    private BookingDTO bookingDTO;

    @BeforeEach
    void setUp() {

        booking = new Booking(
                "BK001",
                LocalDate.of(2026, 6, 10),
                true
        );

        bookingDTO = BookingDTO.builder()
                .bookingReference("BK001")
                .reservedDate(LocalDate.of(2026, 6, 10))
                .bookingStatus(true)
                .build();
    }

    @Test
    void shouldFindBookingById() {

        when(bookingRepository.findById("BK001"))
                .thenReturn(Optional.of(booking));

        BookingDTO result =
                bookingService.findById("BK001");

        assertEquals("BK001",
                result.bookingReference());
    }

    @Test
    void shouldThrowWhenBookingMissing() {

        when(bookingRepository.findById("BK001"))
                .thenReturn(Optional.empty());

        assertThrows(
                BookingNotFoundException.class,
                () -> bookingService.findById("BK001")
        );
    }

    @Test
    void shouldCreateBooking() {

        when(bookingRepository.save(any()))
                .thenReturn(booking);

        BookingDTO result =
                bookingService.createBooking(bookingDTO);

        assertEquals("BK001",
                result.bookingReference());

        verify(bookingRepository).save(any());
    }

    @Test
    void shouldDeleteBooking() {

        when(bookingRepository.existsById("BK001"))
                .thenReturn(true);

        bookingService.deleteBooking("BK001");

        verify(bookingRepository)
                .deleteById("BK001");
    }
}