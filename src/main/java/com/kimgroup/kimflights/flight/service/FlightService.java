package com.kimgroup.kimflights.flight.service;

import com.kimgroup.kimflights.flight.FlightDTO;
import com.kimgroup.kimflights.flight.FlightIdUpdatedEvent;
import com.kimgroup.kimflights.flight.models.Flight;
import com.kimgroup.kimflights.flight.repository.FlightRepository;
import com.kimgroup.kimflights.flight.exception.FlightNotFoundException;
import com.kimgroup.kimflights.flight.mapper.FlightMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;
    private final ApplicationEventPublisher eventPublisher;

    public List<FlightDTO> findAll() {
        return flightRepository.findAll().stream()
            .map(flightMapper::toDTO)
            .toList();
    }

    public List<FlightDTO> search(String origin, String destination, String date) {
        LocalDate localDate = LocalDate.parse(date);
        LocalDateTime start = localDate.atStartOfDay();
        LocalDateTime end = localDate.atTime(LocalTime.MAX);
        return flightRepository.findByOriginCodeAndDestinationCodeAndDepartureDateBetween(origin, destination, start, end)
            .stream()
            .map(flightMapper::toDTO)
            .toList();
    }

    public FlightDTO findById(String id) {
        Flight flight = flightRepository.findById(id)
            .orElseThrow(() -> new FlightNotFoundException(id));
        return flightMapper.toDTO(flight);
    }

    @Transactional
    public FlightDTO create(FlightDTO dto) {
        Flight flight = flightMapper.toEntity(dto);
        Flight saved = flightRepository.save(flight);
        return flightMapper.toDTO(saved);
    }

    @Transactional
    public FlightDTO update(String id, FlightDTO dto) {
        Flight existing = flightRepository.findById(id)
            .orElseThrow(() -> new FlightNotFoundException(id));

        flightMapper.updateEntityFromDTO(existing, dto);
        Flight updated = flightRepository.save(existing);
        return flightMapper.toDTO(updated);
    }

    @Transactional
    public void delete(String id) {
        if (!flightRepository.existsById(id)) {
            throw new FlightNotFoundException(id);
        }
        flightRepository.deleteById(id);
    }

    @Transactional
    public void updateFlightId(String oldId, String newId) {
        flightRepository.updateId(oldId, newId);
        eventPublisher.publishEvent(new FlightIdUpdatedEvent(oldId, newId));
    }
}


