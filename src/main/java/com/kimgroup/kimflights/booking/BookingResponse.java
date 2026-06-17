package com.kimgroup.kimflights.booking.dto.response;

import com.kimgroup.kimflights.booking.dto.PassengerDTO;
import com.kimgroup.kimflights.booking.model.BookingStatus;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
public record BookingResponse(
        String bookingReference,
        LocalDate reservedDate,
        BookingStatus status,
        BigDecimal totalPrice,
        List<PassengerDTO> passengers
) {}