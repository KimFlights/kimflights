package com.kimgroup.kimflights.booking.service;

import java.util.List;

import com.kimgroup.kimflights.booking.dto.AddressDTO;

public interface AddressService {

    List<AddressDTO> findAll();

    AddressDTO findById(String id);

    AddressDTO create(AddressDTO dto);

    AddressDTO update(String id, AddressDTO dto);

    void delete(String id);
}