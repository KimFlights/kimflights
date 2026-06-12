package com.kimgroup.kimflights.booking;

import java.util.Optional;

public interface AddressService {
    Optional<AddressDTO> findById(String id);
}
