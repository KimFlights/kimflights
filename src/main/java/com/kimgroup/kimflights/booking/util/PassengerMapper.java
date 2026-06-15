package com.kimgroup.kimflights.booking.util;

import com.kimgroup.kimflights.booking.dto.AddressDTO;
import com.kimgroup.kimflights.booking.dto.PassengerDTO;
import com.kimgroup.kimflights.booking.model.Address;
import com.kimgroup.kimflights.booking.model.Passenger;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PassengerMapper {

    public PassengerDTO toDTO(Passenger passenger) {

        if (passenger == null) return null;

        return PassengerDTO.builder()
                .id(passenger.getId() != null ? passenger.getId().toString() : null)
                .name(passenger.getName())
                .passportNumber(passenger.getPassportNumber())
                .address(toDTO(passenger.getAddress()))
                .tickets(
                        passenger.getTickets() == null
                                ? List.of()
                                : passenger.getTickets().stream()
                                .map(t -> new TicketMapper().toDTO(t))
                                .toList()
                )
                .luggage(
                        passenger.getLuggage() == null
                                ? List.of()
                                : passenger.getLuggage().stream()
                                .map(l -> new LuggageMapper().toDTO(l))
                                .toList()
                )
                .build();
    }

    public Passenger toEntity(PassengerDTO dto) {

        if (dto == null) return null;

        Passenger passenger = new Passenger();

        passenger.setName(dto.name());
        passenger.setPassportNumber(dto.passportNumber());
        passenger.setAddress(toEntity(dto.address()));

        return passenger;
    }

    private AddressDTO toDTO(Address address) {

        if (address == null) return null;

        return AddressDTO.builder()
                .street(address.getStreet())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .postalCode(address.getPostalCode())
                .build();
    }

    private Address toEntity(AddressDTO dto) {

        if (dto == null) return null;

        return new Address(
                dto.street(),
                dto.city(),
                dto.state(),
                dto.country(),
                dto.postalCode()
        );
    }
}