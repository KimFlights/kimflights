package com.kimgroup.kimflights.flight.repository;

import com.kimgroup.kimflights.flight.models.FlightRoute;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRouteRepository extends JpaRepository<FlightRoute, String> {

    @Override
    @EntityGraph(attributePaths = {"airline", "origin", "destination"})
    List<FlightRoute> findAll();

    @Override
    @EntityGraph(attributePaths = {"airline", "origin", "destination"})
    Optional<FlightRoute> findById(String flightCode);
}
