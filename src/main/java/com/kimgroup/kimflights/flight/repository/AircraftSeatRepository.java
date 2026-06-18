package com.kimgroup.kimflights.flight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.kimgroup.kimflights.flight.models.AircraftSeat;
import java.util.List;

@Repository
public interface AircraftSeatRepository extends JpaRepository<AircraftSeat, String> {
    List<AircraftSeat> findByAircraftName(String aircraftName);
}
