package com.kimgroup.kimflights.flight.mapper;

import com.kimgroup.kimflights.flight.FlightDTO;
import com.kimgroup.kimflights.flight.models.Aircraft;
import com.kimgroup.kimflights.flight.models.Airport;
import com.kimgroup.kimflights.flight.models.Flight;
import com.kimgroup.kimflights.flight.models.FlightRoute;
import com.kimgroup.kimflights.flight.repository.AircraftRepository;
import com.kimgroup.kimflights.flight.repository.AirportRepository;
import com.kimgroup.kimflights.flight.repository.FlightRouteRepository;
import com.kimgroup.kimflights.flight.exception.AircraftNotFoundException;
import com.kimgroup.kimflights.flight.exception.AirportNotFoundException;
import com.kimgroup.kimflights.flight.exception.FlightRouteNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FlightMapper {
    private final AircraftRepository aircraftRepository;
    private final FlightRouteRepository flightRouteRepository;
    private final AirportRepository airportRepository;

    public FlightDTO toDTO(Flight flight) {
        if (flight == null) {
            return null;
        }
        FlightRoute route = flight.getFlightRoute();
        return FlightDTO.builder()
            .id(flight.getId())
            .departureDate(flight.getDepartureDate())
            .arrivalDate(flight.getArrivalDate())
            .distance(flight.getDistance())
            .estimatedTimeInMinutes(flight.getEstimatedTimeInMinutes())
            .flightStatus(flight.getFlightStatus())
            .flightCode(route.getFlightCode())
            .aircraftName(flight.getAircraft().map(Aircraft::getName).orElse(null))
            .airlineName(route.getAirline().getName())
            .originAirportCode(flight.getOriginOverride() != null ? flight.getOriginOverride().getCode() : route.getOrigin().getCode())
            .destinationAirportCode(flight.getDestinationOverride() != null ? flight.getDestinationOverride().getCode() : route.getDestination().getCode())
            .originAirportCodeOverride(flight.getOriginOverride() != null ? flight.getOriginOverride().getCode() : null)
            .destinationAirportCodeOverride(flight.getDestinationOverride() != null ? flight.getDestinationOverride().getCode() : null)
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

        FlightRoute route = flightRouteRepository.findById(dto.flightCode())
            .orElseThrow(() -> new FlightRouteNotFoundException(dto.flightCode()));
        flight.setFlightRoute(route);

        if (dto.originAirportCodeOverride() != null && !dto.originAirportCodeOverride().isBlank()) {
            Airport originOverride = airportRepository.findById(dto.originAirportCodeOverride())
                .orElseThrow(() -> new AirportNotFoundException(dto.originAirportCodeOverride()));
            flight.setOriginOverride(originOverride);
        } else {
            flight.setOriginOverride(null);
        }

        if (dto.destinationAirportCodeOverride() != null && !dto.destinationAirportCodeOverride().isBlank()) {
            Airport destOverride = airportRepository.findById(dto.destinationAirportCodeOverride())
                .orElseThrow(() -> new AirportNotFoundException(dto.destinationAirportCodeOverride()));
            flight.setDestinationOverride(destOverride);
        } else {
            flight.setDestinationOverride(null);
        }
    }
}


