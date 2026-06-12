package com.kimgroup.kimflights.flight.internal;

import org.jspecify.annotations.NullMarked;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@NullMarked
@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {

    @Modifying
    @Query("UPDATE Flight f SET f.id = :newId WHERE f.id = :oldId")
    void updateId(String oldId, String newId);

    @Override
    @EntityGraph(attributePaths = {"aircraft", "airline", "origin", "destination"})
    List<Flight> findAll();
}
