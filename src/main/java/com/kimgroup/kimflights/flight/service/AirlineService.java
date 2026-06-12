package com.kimgroup.kimflights.flight.service;

import com.kimgroup.kimflights.flight.dto.AirlineDTO;
import com.kimgroup.kimflights.flight.repository.AirlineRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AirlineService {
    private final AirlineRepository airlineRepository;

    public AirlineService(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }

    public List<AirlineDTO> findAll() {
        return airlineRepository.findAll().stream()
            .map(airline -> AirlineDTO.builder()
                .id(airline.getId())
                .code(airline.getCode())
                .name(airline.getName())
                .build())
            .toList();
    }
}
