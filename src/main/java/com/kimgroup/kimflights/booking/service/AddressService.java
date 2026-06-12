package com.kimgroup.kimflights.booking.service;

import java.util.Optional;

import com.kimgroup.kimflights.booking.dto.AddressDTO;

public interface AddressService {
    Optional<AddressDTO> findById(String id);
}
