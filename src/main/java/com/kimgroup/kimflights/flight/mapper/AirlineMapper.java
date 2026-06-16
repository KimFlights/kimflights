package com.kimgroup.kimflights.flight.mapper;

import com.kimgroup.kimflights.flight.AirlineDTO;
import com.kimgroup.kimflights.flight.models.Airline;
import org.springframework.stereotype.Component;

@Component
public class AirlineMapper {
    public AirlineDTO toDTO(Airline airline) {
        if (airline == null) {
            return null;
        }
        return AirlineDTO.builder()
                .id(airline.getId())
                .code(airline.getCode())
                .name(airline.getName())
                .build();
    }

    public Airline toEntity(AirlineDTO dto) {
        if (dto == null) {
            return null;
        }
        Airline airline = new Airline();
        airline.setId(dto.id());
        updateEntityFromDTO(airline, dto);
        return airline;
    }

    public void updateEntityFromDTO(Airline airline, AirlineDTO dto) {
        if (dto == null || airline == null) {
            return;
        }
        airline.setCode(dto.code());
        airline.setName(dto.name());
    }
}
