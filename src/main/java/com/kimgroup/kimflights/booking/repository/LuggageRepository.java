package com.kimgroup.kimflights.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kimgroup.kimflights.booking.model.Luggage;

@Repository
public interface LuggageRepository extends JpaRepository<Luggage, Integer> {
}
