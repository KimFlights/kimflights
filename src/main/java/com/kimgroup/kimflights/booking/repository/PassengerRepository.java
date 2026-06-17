package com.kimgroup.kimflights.booking.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kimgroup.kimflights.booking.model.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, UUID> {
}
