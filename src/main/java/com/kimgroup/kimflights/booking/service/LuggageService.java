package com.kimgroup.kimflights.booking.service;

import com.kimgroup.kimflights.booking.model.Luggage;
import com.kimgroup.kimflights.booking.dto.request.AddLuggageRequest;

interface LuggageService {

    Luggage addLuggageToPassenger(
            String passengerId,
            AddLuggageRequest request
    );

    void removeLuggage(
            String passengerId,
            Integer luggageId
    );

    Luggage getLuggage(Integer luggageId);
}