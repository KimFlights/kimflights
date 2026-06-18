package com.kimgroup.kimflights.flight.mapper;

import com.kimgroup.kimflights.flight.AirportDTO;
import com.kimgroup.kimflights.flight.models.Airport;
import org.springframework.stereotype.Component;

@Component
public class AirportMapper {
    public AirportDTO toDTO(Airport airport) {
        if (airport == null) {
            return null;
        }
        return AirportDTO.builder()
                .code(airport.getCode())
                .name(airport.getName())
                .city(airport.getCity())
                .country(airport.getCountry())
                .region(airport.getRegion())
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
        airport.setCity(dto.city());
        airport.setCountry(dto.country());
        airport.setRegion(dto.region());
    }
}
