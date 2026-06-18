package com.kimgroup.kimflights.booking.util;

import com.kimgroup.kimflights.booking.dto.LuggageDTO;
import com.kimgroup.kimflights.booking.model.Luggage;
import org.springframework.stereotype.Component;

@Component
public class LuggageMapper {

    public LuggageDTO toDTO(Luggage luggage) {

        if (luggage == null) return null;

        return LuggageDTO.builder()
                .id(luggage.getId())
                .weight(luggage.getWeight())
                .type(luggage.getType())
                .price(luggage.getPrice())
                .build();
    }

    public Luggage toEntity(LuggageDTO dto) {

        if (dto == null) return null;

        Luggage luggage = new Luggage();

        luggage.setWeight(dto.weight());
        luggage.setType(dto.type());
        luggage.setPrice(dto.price());

        return luggage;
    }
}