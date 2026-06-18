package com.kimgroup.kimflights.flight.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kimgroup.kimflights.flight.models.Flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {

    @EntityGraph(attributePaths = {"aircraft", "airline", "origin", "destination"})
    List<Flight> findAll();

    @EntityGraph(attributePaths = {"aircraft", "airline", "origin", "destination"})
    Optional<Flight> findById(String id);

    @EntityGraph(attributePaths = {"aircraft", "airline", "origin", "destination"})
    List<Flight> findByOriginCodeAndDestinationCodeAndDepartureDateBetween(
        String origin, String destination, LocalDateTime start, LocalDateTime end
    );

    @Modifying
    @Query("UPDATE Flight f SET f.id = :newId WHERE f.id = :oldId")
    void updateId(String oldId, String newId);
}
