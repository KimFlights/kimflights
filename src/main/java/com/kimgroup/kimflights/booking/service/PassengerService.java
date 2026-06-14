package com.kimgroup.kimflights.booking.service;

import com.kimgroup.kimflights.booking.model.Passenger;
import com.kimgroup.kimflights.booking.dto.request.AddPassengerRequest;

import java.util.List;

interface PassengerService {

    Passenger createPassenger(AddPassengerRequest request);

    Passenger addPassengerToBooking(String bookingReference, AddPassengerRequest request);

    void removePassenger(String bookingReference, String passengerId);

    Passenger getPassenger(String passengerId);

    List<Passenger> getPassengersByBooking(String bookingReference);
}