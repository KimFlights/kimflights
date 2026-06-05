package com.kimgroup.kimflights.repository;

import com.kimgroup.kimflights.domain.Aircraft;
import org.springframework.stereotype.Repository;
import java.util.Arrays;
import java.util.List;

/**
 * Replace this with JPA later
 */
@Repository
public class AircraftRepository {
    public List<Aircraft> findAll() {
        return Arrays.asList(
            new Aircraft("737 Max", "Boeing", 189),
            new Aircraft("A320neo", "Airbus", 180)
        );
    }
}
