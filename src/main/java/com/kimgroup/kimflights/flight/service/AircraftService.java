package com.kimgroup.kimflights.flight.service;

import com.kimgroup.kimflights.flight.AircraftDTO;
import com.kimgroup.kimflights.flight.models.Aircraft;
import com.kimgroup.kimflights.flight.repository.AircraftRepository;
import com.kimgroup.kimflights.flight.exception.AircraftNotFoundException;
import com.kimgroup.kimflights.flight.mapper.AircraftMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AircraftService {
    private final AircraftRepository aircraftRepository;
    private final AircraftMapper aircraftMapper;
    private final ApplicationEventPublisher eventPublisher;

    public List<AircraftDTO> findAll() {
        return aircraftRepository.findAll().stream()
            .map(aircraftMapper::toDTO)
            .toList();
    }

    public AircraftDTO findById(String name) {
        Aircraft aircraft = aircraftRepository.findById(name)
            .orElseThrow(() -> new AircraftNotFoundException(name));
        return aircraftMapper.toDTO(aircraft);
    }

    @Transactional
    public AircraftDTO create(AircraftDTO dto) {
        Aircraft aircraft = aircraftMapper.toEntity(dto);
        Aircraft saved = aircraftRepository.save(aircraft);
        return aircraftMapper.toDTO(saved);
    }

    @Transactional
    public AircraftDTO update(String name, AircraftDTO dto) {
        Aircraft existing = aircraftRepository.findById(name)
            .orElseThrow(() -> new AircraftNotFoundException(name));

        aircraftMapper.updateEntityFromDTO(existing, dto);
        Aircraft updated = aircraftRepository.save(existing);
        return aircraftMapper.toDTO(updated);
    }

    @Transactional
    public void delete(String name) {
        if (!aircraftRepository.existsById(name)) {
            throw new AircraftNotFoundException(name);
        }
        aircraftRepository.deleteById(name);
    }

    public void assign(String string) {
        eventPublisher.publishEvent(string);
    }
}

