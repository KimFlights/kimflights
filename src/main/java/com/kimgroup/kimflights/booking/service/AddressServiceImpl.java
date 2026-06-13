package com.kimgroup.kimflights.booking.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kimgroup.kimflights.booking.dto.AddressDTO;
import com.kimgroup.kimflights.booking.exception.AddressNotFoundException;
import com.kimgroup.kimflights.booking.model.Address;
import com.kimgroup.kimflights.booking.repository.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<AddressDTO> findAll() {
        return addressRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public AddressDTO findById(String id) {

        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException(id));

        return toDTO(address);
    }

    @Override
    public AddressDTO create(AddressDTO dto) {

        Address address = toEntity(dto);

        Address saved = addressRepository.save(address);

        return toDTO(saved);
    }

    @Override
    public AddressDTO update(String id, AddressDTO dto) {

        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException(id));

        address.setStreet(dto.street());
        address.setCity(dto.city());
        address.setState(dto.state());
        address.setCountry(dto.country());
        address.setPostalcode(dto.postalcode());

        Address updated = addressRepository.save(address);

        return toDTO(updated);
    }

    @Override
    public void delete(String id) {

        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException(id));

        addressRepository.delete(address);
    }

    private AddressDTO toDTO(Address address) {

        return AddressDTO.builder()
                .id(address.getId())
                .street(address.getStreet())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .postalcode(address.getPostalcode())
                .build();
    }

    private Address toEntity(AddressDTO dto) {
        return new Address(
                dto.id(),
                dto.street(),
                dto.city(),
                dto.state(),
                dto.country(),
                dto.postalcode()
        );
    }
}