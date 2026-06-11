package com.kimgroup.kimflights.flight.internal;

import com.kimgroup.kimflights.flight.AircraftDTO;
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
            .map(aircraft -> new AircraftDTO(
                aircraft.getName(),
                aircraft.getManufacturer(),
                aircraft.getSeatCapacity()
            ))
            .toList();
    }

    public void assign(String string) {
        eventPublisher.publishEvent(string);
    };
}
