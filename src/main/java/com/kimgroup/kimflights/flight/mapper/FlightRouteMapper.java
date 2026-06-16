package com.kimgroup.kimflights.flight.mapper;

import com.kimgroup.kimflights.flight.FlightRouteDTO;
import com.kimgroup.kimflights.flight.models.Airline;
import com.kimgroup.kimflights.flight.models.Airport;
import com.kimgroup.kimflights.flight.models.FlightRoute;
import com.kimgroup.kimflights.flight.repository.AirlineRepository;
import com.kimgroup.kimflights.flight.repository.AirportRepository;
import com.kimgroup.kimflights.flight.exception.AirlineNotFoundException;
import com.kimgroup.kimflights.flight.exception.AirportNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FlightRouteMapper {
    private final AirlineRepository airlineRepository;
    private final AirportRepository airportRepository;

    public FlightRouteDTO toDTO(FlightRoute route) {
        if (route == null) {
            return null;
        }
        return FlightRouteDTO.builder()
                .flightCode(route.getFlightCode())
                .departureTime(route.getDepartureTime())
                .arrivalTime(route.getArrivalTime())
                .airlineName(route.getAirline().getName())
                .originAirportCode(route.getOrigin().getCode())
                .destinationAirportCode(route.getDestination().getCode())
                .build();
    }

    public FlightRoute toEntity(FlightRouteDTO dto) {
        if (dto == null) {
            return null;
        }
        FlightRoute route = new FlightRoute();
        route.setFlightCode(dto.flightCode());
        updateEntityFromDTO(route, dto);
        return route;
    }

    public void updateEntityFromDTO(FlightRoute route, FlightRouteDTO dto) {
        if (dto == null || route == null) {
            return;
        }
        route.setDepartureTime(dto.departureTime());
        route.setArrivalTime(dto.arrivalTime());

        Airline airline = airlineRepository.findByName(dto.airlineName())
                .orElseThrow(() -> new AirlineNotFoundException(dto.airlineName()));
        route.setAirline(airline);

        Airport origin = airportRepository.findById(dto.originAirportCode())
                .orElseThrow(() -> new AirportNotFoundException(dto.originAirportCode()));
        route.setOrigin(origin);

        Airport destination = airportRepository.findById(dto.destinationAirportCode())
                .orElseThrow(() -> new AirportNotFoundException(dto.destinationAirportCode()));
        route.setDestination(destination);
    }
}
