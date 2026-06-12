package com.kimgroup.kimflights.flight.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {

    @Modifying
    @Query("UPDATE Flight f SET f.id = :newId WHERE f.id = :oldId")
    void updateId(String oldId, String newId);
}
