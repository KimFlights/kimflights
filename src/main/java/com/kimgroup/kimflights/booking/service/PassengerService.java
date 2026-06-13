package com.kimgroup.kimflights.booking.service;

import com.kimgroup.kimflights.booking.dto.PassengerDTO;
import com.kimgroup.kimflights.booking.exception.BookingNotFoundException;
import com.kimgroup.kimflights.booking.exception.PassengerNotFoundException;
import com.kimgroup.kimflights.booking.model.Booking;
import com.kimgroup.kimflights.booking.model.Passenger;
import com.kimgroup.kimflights.booking.repository.PassengerRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {

    private final PassengerRepository passengerRepository;

    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    // ------------------ READ ALL ------------------

    public List<PassengerDTO> findAll() {
        return passengerRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }

    // ------------------ READ BY ID ------------------

    public PassengerDTO findById(String id) {
        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new PassengerNotFoundException(id));

        return mapToDTO(passenger);
    }

    // ------------------ CREATE ------------------

    public PassengerDTO createPassenger(PassengerDTO dto) {

        Passenger passenger = new Passenger();

        passenger.setId(dto.id());
        passenger.setName(dto.name());
        passenger.setPassportNumber(dto.passportNumber());

        Passenger saved = passengerRepository.save(passenger);

        return mapToDTO(saved);
    }

    // ------------------ UPDATE ------------------

    public PassengerDTO updatePassenger(String id, PassengerDTO dto) {

        Passenger existing = passengerRepository.findById(id)
                .orElseThrow(() -> new PassengerNotFoundException(id));

        existing.setName(dto.name());
        existing.setPassportNumber(dto.passportNumber());

        Passenger updated = passengerRepository.save(existing);

        return mapToDTO(updated);
    }

    // ------------------ DELETE ------------------

    public void deletePassenger(String id) {

        if (!passengerRepository.existsById(id)) {
            throw new PassengerNotFoundException(id);
        }

        passengerRepository.deleteById(id);
    }

    // ------------------ MAPPER ------------------

    private PassengerDTO mapToDTO(Passenger passenger) {
        return PassengerDTO.builder()
                .id(passenger.getId())
                .name(passenger.getName())
                .passportNumber(passenger.getPassportNumber())
                .build();
    }
}