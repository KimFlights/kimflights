package com.kimgroup.kimflights.booking.service;

import com.kimgroup.kimflights.booking.dto.PassengerDTO;
import com.kimgroup.kimflights.booking.repository.PassengerRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PassengerService {
    private final PassengerRepository passengerRepository;

    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public List<PassengerDTO> findAll() {
        return passengerRepository.findAll().stream()
            .map(passenger -> PassengerDTO.builder()
                .id(passenger.getId())
                .name(passenger.getName())
                .passportNumber(passenger.getPassportNumber())
                .build())
            .toList();
    }
}
