package com.kimgroup.kimflights.flight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kimgroup.kimflights.flight.models.Aircraft;

@Repository
public interface AircraftRepository extends JpaRepository<Aircraft, String> {
}
