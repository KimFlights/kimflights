package com.kimgroup.kimflights.flight.internal;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
class AircraftService {
    private final AircraftRepository aircraftRepository;

    public AircraftService(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
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
}
