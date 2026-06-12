package com.kimgroup.kimflights.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kimgroup.kimflights.booking.model.Passenger;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, String> {
}
