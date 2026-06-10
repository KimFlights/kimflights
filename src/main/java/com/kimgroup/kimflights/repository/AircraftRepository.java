package com.kimgroup.kimflights.repository;

import com.kimgroup.kimflights.domain.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Arrays;
import java.util.List;

@Repository
public interface AircraftRepository extends JpaRepository<Aircraft, String> {
}
