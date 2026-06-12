package com.kimgroup.kimflights.booking;

import lombok.Builder;

@Builder
public record AddressDTO(
    String id,
    String street,
    String city,
    String state,
    String country,
    String postalcode
) {}
