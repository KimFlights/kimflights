package com.kimgroup.kimflights.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kimgroup.kimflights.booking.model.Luggage;

public interface LuggageRepository extends JpaRepository<Luggage, Integer> {
}
