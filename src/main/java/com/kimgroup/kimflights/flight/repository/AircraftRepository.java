package com.kimgroup.kimflights.flight.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kimgroup.kimflights.flight.models.Aircraft;
import java.util.List;
import java.util.Optional;

@Repository
public interface AircraftRepository extends JpaRepository<Aircraft, String> {

    @EntityGraph(attributePaths = "seats")
    List<Aircraft> findAll();

    @EntityGraph(attributePaths = "seats")
    Optional<Aircraft> findById(String name);
}
