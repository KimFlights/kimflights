package com.kimgroup.kimflights.flight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kimgroup.kimflights.flight.models.Airline;

@Repository
public interface AirlineRepository extends JpaRepository<Airline, Integer> {
}
