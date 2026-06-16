package com.kimgroup.kimflights.flight.service;

import com.kimgroup.kimflights.flight.AirlineDTO;
import com.kimgroup.kimflights.flight.models.Airline;
import com.kimgroup.kimflights.flight.repository.AirlineRepository;
import com.kimgroup.kimflights.flight.exception.AirlineNotFoundException;
import com.kimgroup.kimflights.flight.mapper.AirlineMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AirlineService {
    private final AirlineRepository airlineRepository;
    private final AirlineMapper airlineMapper;

    public List<AirlineDTO> findAll() {
        return airlineRepository.findAll().stream()
            .map(airlineMapper::toDTO)
            .toList();
    }

    public AirlineDTO findById(Integer id) {
        Airline airline = airlineRepository.findById(id)
            .orElseThrow(() -> new AirlineNotFoundException(id));
        return airlineMapper.toDTO(airline);
    }

    @Transactional
    public AirlineDTO create(AirlineDTO dto) {
        Airline airline = airlineMapper.toEntity(dto);
        Airline saved = airlineRepository.save(airline);
        return airlineMapper.toDTO(saved);
    }

    @Transactional
    public AirlineDTO update(Integer id, AirlineDTO dto) {
        Airline existing = airlineRepository.findById(id)
            .orElseThrow(() -> new AirlineNotFoundException(id));

        airlineMapper.updateEntityFromDTO(existing, dto);
        Airline updated = airlineRepository.save(existing);
        return airlineMapper.toDTO(updated);
    }

    @Transactional
    public void delete(Integer id) {
        if (!airlineRepository.existsById(id)) {
            throw new AirlineNotFoundException(id);
        }
        airlineRepository.deleteById(id);
    }
}

