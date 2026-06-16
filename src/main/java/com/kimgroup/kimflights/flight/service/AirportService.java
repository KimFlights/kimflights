package com.kimgroup.kimflights.flight.service;

import com.kimgroup.kimflights.flight.AirportDTO;
import com.kimgroup.kimflights.flight.models.Airport;
import com.kimgroup.kimflights.flight.repository.AirportRepository;
import com.kimgroup.kimflights.flight.exception.AirportNotFoundException;
import com.kimgroup.kimflights.flight.mapper.AirportMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportService {
    private final AirportRepository airportRepository;
    private final AirportMapper airportMapper;

    public List<AirportDTO> findAll() {
        return airportRepository.findAll().stream()
            .map(airportMapper::toDTO)
            .toList();
    }

    public AirportDTO findById(String code) {
        Airport airport = airportRepository.findById(code)
            .orElseThrow(() -> new AirportNotFoundException(code));
        return airportMapper.toDTO(airport);
    }

    @Transactional
    public AirportDTO create(AirportDTO dto) {
        Airport airport = airportMapper.toEntity(dto);
        Airport saved = airportRepository.save(airport);
        return airportMapper.toDTO(saved);
    }

    @Transactional
    public AirportDTO update(String code, AirportDTO dto) {
        Airport existing = airportRepository.findById(code)
            .orElseThrow(() -> new AirportNotFoundException(code));

        airportMapper.updateEntityFromDTO(existing, dto);
        Airport updated = airportRepository.save(existing);
        return airportMapper.toDTO(updated);
    }

    @Transactional
    public void delete(String code) {
        if (!airportRepository.existsById(code)) {
            throw new AirportNotFoundException(code);
        }
        airportRepository.deleteById(code);
    }
}

