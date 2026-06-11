package com.kimgroup.kimflights.booking.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {

    @Modifying
    @Query("UPDATE Ticket t SET t.flightId = :newFlightId WHERE t.flightId = :oldFlightId")
    void updateFlightId(String oldFlightId, String newFlightId);
}
