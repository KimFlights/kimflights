package com.kimgroup.kimflights.flight.service;

import com.kimgroup.kimflights.flight.dto.AirportDTO;
import com.kimgroup.kimflights.flight.repository.AirportRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AirportService {
    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public List<AirportDTO> findAll() {
        return airportRepository.findAll().stream()
            .map(airport -> AirportDTO.builder()
                .code(airport.getCode())
                .addressId(airport.getAddressId())
                .build())
            .toList();
    }
}
