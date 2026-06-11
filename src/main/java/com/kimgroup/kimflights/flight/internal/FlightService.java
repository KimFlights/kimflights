package com.kimgroup.kimflights.flight.internal;

import com.kimgroup.kimflights.flight.FlightDTO;
import com.kimgroup.kimflights.flight.FlightIdUpdatedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class FlightService {
    private final FlightRepository flightRepository;
    private final ApplicationEventPublisher eventPublisher;

    public FlightService(FlightRepository flightRepository, ApplicationEventPublisher eventPublisher) {
        this.flightRepository = flightRepository;
        this.eventPublisher = eventPublisher;
    }

    public List<FlightDTO> findAll() {
        return flightRepository.findAll().stream()
            .map(flight -> new FlightDTO(
                flight.getId(),
                flight.getDepartureDate(),
                flight.getArrivalDate(),
                flight.getDistance(),
                flight.getEstimatedTimeInMinutes()
            ))
            .toList();
    }

    @Transactional
    public void updateFlightId(String oldId, String newId) {
        flightRepository.updateId(oldId, newId);
        eventPublisher.publishEvent(new FlightIdUpdatedEvent(oldId, newId));
    }
}
