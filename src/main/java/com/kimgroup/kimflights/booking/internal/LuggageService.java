package com.kimgroup.kimflights.booking.internal;

import com.kimgroup.kimflights.booking.LuggageDTO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LuggageService {
    private final LuggageRepository luggageRepository;

    public LuggageService(LuggageRepository luggageRepository) {
        this.luggageRepository = luggageRepository;
    }

    public List<LuggageDTO> findAll() {
        return luggageRepository.findAll().stream()
            .map(luggage -> LuggageDTO.builder()
                .id(luggage.getId())
                .weight(luggage.getWeight())
                .type(luggage.getType())
                .price(luggage.getPrice())
                .build())
            .toList();
    }
}
