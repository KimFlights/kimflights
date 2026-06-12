package com.kimgroup.kimflights.flight.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface AircraftRepository extends JpaRepository<Aircraft, String> {
}
