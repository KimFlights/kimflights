package com.kimgroup.kimflights.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.kimgroup.kimflights.booking.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, String> {

    @Modifying
    @Query("UPDATE Ticket t SET t.flightId = :newFlightId WHERE t.flightId = :oldFlightId")
    void updateFlightId(String oldFlightId, String newFlightId);
}
