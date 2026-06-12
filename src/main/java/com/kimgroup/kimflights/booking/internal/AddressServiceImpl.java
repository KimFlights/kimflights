package com.kimgroup.kimflights.booking.internal;

import com.kimgroup.kimflights.booking.AddressDTO;
import com.kimgroup.kimflights.booking.AddressService;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Optional<AddressDTO> findById(String id) {
        if (id == null) {
            return Optional.empty();
        }
        return addressRepository.findById(id)
            .map(address -> AddressDTO.builder()
                .id(address.getId())
                .street(address.getStreet())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .postalcode(address.getPostalcode())
                .build());
    }
}
