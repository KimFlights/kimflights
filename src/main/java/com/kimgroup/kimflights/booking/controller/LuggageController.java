package com.kimgroup.kimflights.booking.controller;

import com.kimgroup.kimflights.booking.dto.LuggageDTO;
import com.kimgroup.kimflights.booking.service.LuggageService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/luggage")
public class LuggageController {

    private final LuggageService luggageService;

    public LuggageController(LuggageService luggageService) {
        this.luggageService = luggageService;
    }

    // GET ALL

    @GetMapping
    public List<LuggageDTO> findAll() {
        return luggageService.findAll();
    }

    // GET BY ID

    @GetMapping("/{id}")
    public LuggageDTO findById(@PathVariable Integer id) {
        return luggageService.findById(id);
    }

    // CREATE

    @PostMapping
    public LuggageDTO create(
            @Valid @RequestBody LuggageDTO dto) {

        return luggageService.createLuggage(dto);
    }

    // UPDATE

    @PutMapping("/{id}")
    public LuggageDTO update(
            @PathVariable Integer id,
            @Valid @RequestBody LuggageDTO dto) {

        return luggageService.updateLuggage(id, dto);
    }

    // DELETE

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        luggageService.deleteLuggage(id);
    }
}