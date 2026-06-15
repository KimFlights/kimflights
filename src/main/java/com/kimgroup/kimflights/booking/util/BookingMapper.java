package com.kimgroup.kimflights.booking.util;

import com.kimgroup.kimflights.booking.dto.BookingDTO;
import com.kimgroup.kimflights.booking.model.Booking;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookingMapper {

    private final PassengerMapper passengerMapper = new PassengerMapper();

    public BookingDTO toDTO(Booking booking) {

        if (booking == null) return null;

        return BookingDTO.builder()
                .bookingReference(booking.getBookingReference())
                .reservedDate(booking.getReservedDate())
                .status(booking.getStatus())
                .passengers(
                        booking.getPassengers() == null
                                ? List.of()
                                : booking.getPassengers()
                                .stream()
                                .map(passengerMapper::toDTO)
                                .toList()
                )
                .build();
    }
}