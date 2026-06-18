package com.kimgroup.kimflights.flight.mapper;

import com.kimgroup.kimflights.flight.AirportDTO;
import com.kimgroup.kimflights.flight.models.Airport;
import com.kimgroup.kimflights.flight.models.AirportLocation;
import org.springframework.stereotype.Component;

@Component
public class AirportMapper {

    public AirportDTO toDTO(Airport airport) {
        if (airport == null) {
            return null;
        }
        AirportLocation loc = airport.getLocation();
        return AirportDTO.builder()
                .code(airport.getCode())
                .name(airport.getName())
                .city(loc != null ? loc.getCity() : null)
                .country(loc != null ? loc.getCountry() : null)
                .region(loc != null ? loc.getState() : null)
                .build();
    }

    public Airport toEntity(AirportDTO dto) {
        if (dto == null) {
            return null;
        }
        Airport airport = new Airport();
        airport.setCode(dto.code());
        updateEntityFromDTO(airport, dto);
        return airport;
    }

    public void updateEntityFromDTO(Airport airport, AirportDTO dto) {
        if (dto == null || airport == null) {
            return;
        }
        airport.setName(dto.name());
        airport.setRegion(dto.region());
        AirportLocation loc = airport.getLocation() != null
                ? airport.getLocation()
                : new AirportLocation();
        loc.setCity(dto.city());
        loc.setCountry(dto.country());
        loc.setState(dto.region());
        airport.setLocation(loc);
    }
}
