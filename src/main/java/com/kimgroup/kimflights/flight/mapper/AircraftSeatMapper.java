package com.kimgroup.kimflights.flight.mapper;

import com.kimgroup.kimflights.flight.AircraftSeatDTO;
import com.kimgroup.kimflights.flight.models.Aircraft;
import com.kimgroup.kimflights.flight.models.AircraftSeat;
import com.kimgroup.kimflights.flight.repository.AircraftRepository;
import com.kimgroup.kimflights.flight.exception.AircraftNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AircraftSeatMapper {

    private final AircraftRepository aircraftRepository;

    public AircraftSeatDTO toDTO(AircraftSeat seat) {
        if (seat == null) {
            return null;
        }
        return AircraftSeatDTO.builder()
                .id(seat.getId())
                .aircraftId(seat.getAircraft() != null ? seat.getAircraft().getName() : null)
                .seatNumber(seat.getSeatNumber())
                .cabin(seat.getCabin())
                .build();
    }

    public AircraftSeat toEntity(AircraftSeatDTO dto) {
        if (dto == null) {
            return null;
        }
        AircraftSeat seat = new AircraftSeat();
        seat.setId(dto.id());
        updateEntityFromDTO(seat, dto);
        return seat;
    }

    public void updateEntityFromDTO(AircraftSeat seat, AircraftSeatDTO dto) {
        if (dto == null || seat == null) {
            return;
        }
        seat.setSeatNumber(dto.seatNumber());
        seat.setCabin(dto.cabin());
        
        if (dto.aircraftId() != null) {
            Aircraft aircraft = aircraftRepository.findById(dto.aircraftId())
                    .orElseThrow(() -> new AircraftNotFoundException(dto.aircraftId()));
            seat.setAircraft(aircraft);
        } else {
            seat.setAircraft(null);
        }
    }
}
