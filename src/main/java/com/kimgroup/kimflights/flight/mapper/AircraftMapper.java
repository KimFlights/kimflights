package com.kimgroup.kimflights.flight.mapper;

import com.kimgroup.kimflights.flight.AircraftDTO;
import com.kimgroup.kimflights.flight.models.Aircraft;
import org.springframework.stereotype.Component;

@Component
public class AircraftMapper {
    public AircraftDTO toDTO(Aircraft aircraft) {
        if (aircraft == null) {
            return null;
        }
        return AircraftDTO.builder()
                .name(aircraft.getName())
                .manufacturer(aircraft.getManufacturer())
                .seatCapacity(aircraft.getSeatCapacity())
                .build();
    }

    public Aircraft toEntity(AircraftDTO dto) {
        if (dto == null) {
            return null;
        }
        Aircraft aircraft = new Aircraft();
        aircraft.setName(dto.name());
        updateEntityFromDTO(aircraft, dto);
        return aircraft;
    }

    public void updateEntityFromDTO(Aircraft aircraft, AircraftDTO dto) {
        if (dto == null || aircraft == null) {
            return;
        }
        aircraft.setManufacturer(dto.manufacturer());
        aircraft.setSeatCapacity(dto.seatCapacity());
    }
}
