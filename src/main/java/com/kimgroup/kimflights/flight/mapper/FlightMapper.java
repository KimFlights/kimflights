package com.kimgroup.kimflights.flight.mapper;

import com.kimgroup.kimflights.flight.FlightDTO;
import com.kimgroup.kimflights.flight.models.Aircraft;
import com.kimgroup.kimflights.flight.models.Airline;
import com.kimgroup.kimflights.flight.models.Airport;
import com.kimgroup.kimflights.flight.models.Flight;
import com.kimgroup.kimflights.flight.repository.AircraftRepository;
import com.kimgroup.kimflights.flight.repository.AirlineRepository;
import com.kimgroup.kimflights.flight.repository.AirportRepository;
import com.kimgroup.kimflights.flight.exception.AircraftNotFoundException;
import com.kimgroup.kimflights.flight.exception.AirlineNotFoundException;
import com.kimgroup.kimflights.flight.exception.AirportNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FlightMapper {
    private final AircraftRepository aircraftRepository;
    private final AirlineRepository airlineRepository;
    private final AirportRepository airportRepository;

    public FlightDTO toDTO(Flight flight) {
        if (flight == null) {
            return null;
        }
        return FlightDTO.builder()
            .id(flight.getId())
            .departureDate(flight.getDepartureDate())
            .arrivalDate(flight.getArrivalDate())
            .distance(flight.getDistance())
            .estimatedTimeInMinutes(flight.getEstimatedTimeInMinutes())
            .flightStatus(flight.getFlightStatus())
            .aircraftName(flight.getAircraft().map(Aircraft::getName).orElse(null))
            .seatCapacity(flight.getAircraft().map(Aircraft::getSeatCapacity).orElse(150)) // Fallback to 150
            .airlineName(flight.getAirline().getName())
            .originAirportCode(flight.getOrigin().getCode())
            .destinationAirportCode(flight.getDestination().getCode())
            .build();
    }

    public Flight toEntity(FlightDTO dto) {
        if (dto == null) {
            return null;
        }
        Flight flight = new Flight();
        flight.setId(dto.id());
        updateEntityFromDTO(flight, dto);
        return flight;
    }

    public void updateEntityFromDTO(Flight flight, FlightDTO dto) {
        if (dto == null || flight == null) {
            return;
        }
        flight.setDepartureDate(dto.departureDate());
        flight.setArrivalDate(dto.arrivalDate());
        flight.setDistance(dto.distance());
        flight.setEstimatedTimeInMinutes(dto.estimatedTimeInMinutes());
        flight.setFlightStatus(dto.flightStatus());

        if (dto.aircraftName() != null) {
            Aircraft aircraft = aircraftRepository.findById(dto.aircraftName())
                .orElseThrow(() -> new AircraftNotFoundException(dto.aircraftName()));
            flight.setAircraft(aircraft);
        } else {
            flight.setAircraft(null);
        }

        Airline airline = airlineRepository.findByName(dto.airlineName())
            .orElseThrow(() -> new AirlineNotFoundException(dto.airlineName()));
        flight.setAirline(airline);

        Airport origin = airportRepository.findById(dto.originAirportCode())
            .orElseThrow(() -> new AirportNotFoundException(dto.originAirportCode()));
        flight.setOrigin(origin);

        Airport destination = airportRepository.findById(dto.destinationAirportCode())
            .orElseThrow(() -> new AirportNotFoundException(dto.destinationAirportCode()));
        flight.setDestination(destination);
    }
}
