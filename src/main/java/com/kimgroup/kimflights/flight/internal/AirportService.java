package com.kimgroup.kimflights.flight.internal;

import com.kimgroup.kimflights.flight.AirportDTO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
class AirportService {
    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public List<AirportDTO> findAll() {
        return airportRepository.findAll().stream()
            .map(airport -> AirportDTO.builder()
                .id(airport.getId())
                .code(airport.getCode())
                .name(airport.getName())
                .build())
            .toList();
    }
}
