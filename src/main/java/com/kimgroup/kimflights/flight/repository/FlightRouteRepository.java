package com.kimgroup.kimflights.flight.repository;

import com.kimgroup.kimflights.flight.models.FlightRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRouteRepository extends JpaRepository<FlightRoute, String> {
}
