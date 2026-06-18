package com.kimgroup.kimflights.booking.repository;

import com.kimgroup.kimflights.booking.model.Booking;
import com.kimgroup.kimflights.booking.model.BookingStatus;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, String> {

    List<Booking> findByStatus(BookingStatus status);

    List<Booking> findByReservedDate(LocalDate reservedDate);

    List<Booking> findByUsername(String username);

    boolean existsByBookingReference(String bookingReference);
}