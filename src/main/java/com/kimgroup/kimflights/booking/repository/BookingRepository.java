package com.kimgroup.kimflights.booking.repository;

import com.kimgroup.kimflights.booking.model.Booking;
import com.kimgroup.kimflights.booking.model.BookingStatus;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {

    List<Booking> findByStatus(BookingStatus status);

    List<Booking> findByReservedDate(LocalDate reservedDate);

    boolean existsByBookingReference(String bookingReference);
}