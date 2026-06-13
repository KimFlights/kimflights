package com.kimgroup.kimflights.booking.service;

import com.kimgroup.kimflights.booking.dto.LuggageDTO;
import com.kimgroup.kimflights.booking.exception.LuggageNotFoundException;
import com.kimgroup.kimflights.booking.model.Luggage;
import com.kimgroup.kimflights.booking.repository.LuggageRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LuggageService {

    private final LuggageRepository luggageRepository;

    public LuggageService(LuggageRepository luggageRepository) {
        this.luggageRepository = luggageRepository;
    }

    // ------------------ READ ALL ------------------

    public List<LuggageDTO> findAll() {
        return luggageRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }

    // ------------------ READ BY ID ------------------

    public LuggageDTO findById(Integer id) {
        Luggage luggage = luggageRepository.findById(id)
                .orElseThrow(() -> new LuggageNotFoundException(id));

        return mapToDTO(luggage);
    }

    // ------------------ CREATE ------------------

    public LuggageDTO createLuggage(LuggageDTO dto) {

        Luggage luggage = new Luggage();

        luggage.setWeight(dto.weight());
        luggage.setType(dto.type());
        luggage.setPrice(dto.price());

        Luggage saved = luggageRepository.save(luggage);

        return mapToDTO(saved);
    }

    // ------------------ UPDATE ------------------

    public LuggageDTO updateLuggage(Integer id, LuggageDTO dto) {

        Luggage luggage = luggageRepository.findById(id)
                .orElseThrow(() -> new LuggageNotFoundException(id));

        luggage.setWeight(dto.weight());
        luggage.setType(dto.type());
        luggage.setPrice(dto.price());

        Luggage updated = luggageRepository.save(luggage);

        return mapToDTO(updated);
    }

    // ------------------ DELETE ------------------

    public void deleteLuggage(Integer id) {

        if (!luggageRepository.existsById(id)) {
            throw new LuggageNotFoundException(id);
        }

        luggageRepository.deleteById(id);
    }

    // ------------------ DTO MAPPER ------------------

    private LuggageDTO mapToDTO(Luggage luggage) {
        return LuggageDTO.builder()
                .id(luggage.getId())
                .weight(luggage.getWeight())
                .type(luggage.getType())
                .price(luggage.getPrice())
                .build();
    }
}