package com.kimgroup.kimflights.booking;

import lombok.Builder;

@Builder
public record PassengerDTO(
    String id,
    String name,
    String passportNumber
) {}
