package com.kimgroup.kimflights.flight.service;

import com.kimgroup.kimflights.flight.dto.AircraftDTO;
import com.kimgroup.kimflights.flight.repository.AircraftRepository;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
class AircraftService {
    private final AircraftRepository aircraftRepository;
    private final ApplicationEventPublisher eventPublisher;

    public AircraftService(AircraftRepository aircraftRepository, ApplicationEventPublisher eventPublisher) {
        this.aircraftRepository = aircraftRepository;
        this.eventPublisher = eventPublisher;
    }

    public List<AircraftDTO> findAll() {
        return aircraftRepository.findAll().stream()
            .map(aircraft -> AircraftDTO.builder()
                .name(aircraft.getName())
                .manufacturer(aircraft.getManufacturer())
                .seatCapacity(aircraft.getSeatCapacity())
                .build())
            .toList();
    }

    public void assign(String string) {
        eventPublisher.publishEvent(string);
    }
}
