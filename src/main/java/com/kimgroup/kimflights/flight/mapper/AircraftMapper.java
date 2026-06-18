package com.kimgroup.kimflights.flight.mapper;

import com.kimgroup.kimflights.flight.AircraftDTO;
import com.kimgroup.kimflights.flight.models.Aircraft;
import com.kimgroup.kimflights.flight.models.AircraftSeat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AircraftMapper {
    private final AircraftSeatMapper aircraftSeatMapper;

    public AircraftDTO toDTO(Aircraft aircraft) {
        if (aircraft == null) {
            return null;
        }
        return AircraftDTO.builder()
                .name(aircraft.getName())
                .manufacturer(aircraft.getManufacturer())
                .seatCapacity(aircraft.getSeatCapacity())
                .seats(aircraft.getSeats() != null ?
                        aircraft.getSeats().stream().map(aircraftSeatMapper::toDTO).toList() : null)
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

        if (dto.seats() != null) {
            // Synchronize seats in place to avoid primary key/unique index conflicts in Hibernate
            Map<String, AircraftSeat> existingSeats = aircraft.getSeats().stream()
                    .collect(Collectors.toMap(AircraftSeat::getId, Function.identity()));

            Set<String> incomingIds = dto.seats().stream()
                    .map(com.kimgroup.kimflights.flight.AircraftSeatDTO::id)
                    .collect(Collectors.toSet());

            // Remove seats that are not in the new payload
            java.util.List<AircraftSeat> toRemove = aircraft.getSeats().stream()
                    .filter(seat -> !incomingIds.contains(seat.getId()))
                    .toList();
            toRemove.forEach(aircraft::removeSeat);

            // Add new seats or update existing ones
            dto.seats().forEach(seatDto -> {
                AircraftSeat seat = existingSeats.get(seatDto.id());
                if (seat != null) {
                    aircraftSeatMapper.updateEntityFromDTO(seat, seatDto);
                } else {
                    AircraftSeat newSeat = aircraftSeatMapper.toEntity(seatDto);
                    aircraft.addSeat(newSeat);
                }
            });
        }
    }
}
