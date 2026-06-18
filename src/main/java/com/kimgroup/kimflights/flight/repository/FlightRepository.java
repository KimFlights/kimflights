package com.kimgroup.kimflights.flight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kimgroup.kimflights.flight.models.Flight;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {

    @Modifying
    @Query("UPDATE Flight f SET f.id = :newId WHERE f.id = :oldId")
    void updateId(String oldId, String newId);

    @Query("""
        SELECT f FROM Flight f
        WHERE f.origin.code = :origin
          AND f.destination.code = :destination
          AND f.departureDate >= :dayStart
          AND f.departureDate < :dayEnd
        """)
    List<Flight> searchByRoute(
        @Param("origin") String origin,
        @Param("destination") String destination,
        @Param("dayStart") LocalDateTime dayStart,
        @Param("dayEnd") LocalDateTime dayEnd
    );
}
