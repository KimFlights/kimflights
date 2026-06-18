package com.kimgroup.kimflights.flight.service;

import com.kimgroup.kimflights.flight.AircraftSeatDTO;
import com.kimgroup.kimflights.flight.models.AircraftSeat;
import com.kimgroup.kimflights.flight.repository.AircraftSeatRepository;
import com.kimgroup.kimflights.flight.exception.AircraftSeatNotFoundException;
import com.kimgroup.kimflights.flight.mapper.AircraftSeatMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AircraftSeatService {
    private final AircraftSeatRepository aircraftSeatRepository;
    private final AircraftSeatMapper aircraftSeatMapper;

    public List<AircraftSeatDTO> findAll() {
        return aircraftSeatRepository.findAll().stream()
            .map(aircraftSeatMapper::toDTO)
            .toList();
    }

    public AircraftSeatDTO findById(String id) {
        AircraftSeat seat = aircraftSeatRepository.findById(id)
            .orElseThrow(() -> new AircraftSeatNotFoundException(id));
        return aircraftSeatMapper.toDTO(seat);
    }

    @Transactional
    public AircraftSeatDTO create(AircraftSeatDTO dto) {
        AircraftSeat seat = aircraftSeatMapper.toEntity(dto);
        AircraftSeat saved = aircraftSeatRepository.save(seat);
        return aircraftSeatMapper.toDTO(saved);
    }

    @Transactional
    public AircraftSeatDTO update(String id, AircraftSeatDTO dto) {
        AircraftSeat existing = aircraftSeatRepository.findById(id)
            .orElseThrow(() -> new AircraftSeatNotFoundException(id));

        aircraftSeatMapper.updateEntityFromDTO(existing, dto);
        AircraftSeat updated = aircraftSeatRepository.save(existing);
        return aircraftSeatMapper.toDTO(updated);
    }

    @Transactional
    public void delete(String id) {
        if (!aircraftSeatRepository.existsById(id)) {
            throw new AircraftSeatNotFoundException(id);
        }
        aircraftSeatRepository.deleteById(id);
    }
}
