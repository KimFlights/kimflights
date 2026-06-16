package com.kimgroup.kimflights.flight.service;

import com.kimgroup.kimflights.flight.FlightRouteDTO;
import com.kimgroup.kimflights.flight.models.FlightRoute;
import com.kimgroup.kimflights.flight.repository.FlightRouteRepository;
import com.kimgroup.kimflights.flight.exception.FlightRouteNotFoundException;
import com.kimgroup.kimflights.flight.mapper.FlightRouteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightRouteService {
    private final FlightRouteRepository flightRouteRepository;
    private final FlightRouteMapper flightRouteMapper;

    public List<FlightRouteDTO> findAll() {
        return flightRouteRepository.findAll().stream()
                .map(flightRouteMapper::toDTO)
                .toList();
    }

    public FlightRouteDTO findById(String flightCode) {
        FlightRoute route = flightRouteRepository.findById(flightCode)
                .orElseThrow(() -> new FlightRouteNotFoundException(flightCode));
        return flightRouteMapper.toDTO(route);
    }

    @Transactional
    public FlightRouteDTO create(FlightRouteDTO dto) {
        FlightRoute route = flightRouteMapper.toEntity(dto);
        FlightRoute saved = flightRouteRepository.save(route);
        return flightRouteMapper.toDTO(saved);
    }

    @Transactional
    public FlightRouteDTO update(String flightCode, FlightRouteDTO dto) {
        FlightRoute existing = flightRouteRepository.findById(flightCode)
                .orElseThrow(() -> new FlightRouteNotFoundException(flightCode));

        flightRouteMapper.updateEntityFromDTO(existing, dto);
        FlightRoute updated = flightRouteRepository.save(existing);
        return flightRouteMapper.toDTO(updated);
    }

    @Transactional
    public void delete(String flightCode) {
        if (!flightRouteRepository.existsById(flightCode)) {
            throw new FlightRouteNotFoundException(flightCode);
        }
        flightRouteRepository.deleteById(flightCode);
    }
}
