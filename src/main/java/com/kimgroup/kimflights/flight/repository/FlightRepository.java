package com.kimgroup.kimflights.flight.repository;

import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kimgroup.kimflights.flight.models.Flight;

import java.util.List;
import java.util.Optional;

@Repository
@NullMarked
public interface FlightRepository extends JpaRepository<Flight, String> {

    @Modifying
    @Query("UPDATE Flight f SET f.id = :newId WHERE f.id = :oldId")
    void updateId(String oldId, String newId);

    @Override
    @EntityGraph(attributePaths = {
        "aircraft",
        "flightRoute",
        "originOverride",
        "destinationOverride",
        "flightRoute.airline",
        "flightRoute.origin",
        "flightRoute.destination"
    })
    List<Flight> findAll();

    @Override
    @EntityGraph(attributePaths = {
        "aircraft",
        "flightRoute",
        "originOverride",
        "destinationOverride",
        "flightRoute.airline",
        "flightRoute.origin",
        "flightRoute.destination"
    })
    Optional<Flight> findById(String id);
}
