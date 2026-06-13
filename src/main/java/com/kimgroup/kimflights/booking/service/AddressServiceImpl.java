package com.kimgroup.kimflights.booking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kimgroup.kimflights.booking.dto.AddressDTO;
import com.kimgroup.kimflights.booking.model.Address;
import com.kimgroup.kimflights.booking.repository.AddressRepository;

@Service
class AddressServiceImpl implements AddressService {

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
    public Optional<AddressDTO> findById(String id) {
        if (id == null) {
            return Optional.empty();
        }

        return addressRepository.findById(id)
                .map(this::toDTO);
    }

    @Override
    public AddressDTO create(AddressDTO dto) {

        Address address = new Address(
                dto.id(),
                dto.street(),
                dto.city(),
                dto.state(),
                dto.country(),
                dto.postalcode()
        );

        return toDTO(addressRepository.save(address));
    }

    @Override
    public AddressDTO update(String id, AddressDTO dto) {

        Address address = addressRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Address not found: " + id));

        address.setStreet(dto.street());
        address.setCity(dto.city());
        address.setState(dto.state());
        address.setCountry(dto.country());
        address.setPostalcode(dto.postalcode());

        return toDTO(addressRepository.save(address));
    }

    @Override
    public void delete(String id) {
        if (!addressRepository.existsById(id)) {
            throw new RuntimeException("Address not found: " + id);
        }

        addressRepository.deleteById(id);
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
}