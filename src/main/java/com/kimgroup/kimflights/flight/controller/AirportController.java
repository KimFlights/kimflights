package com.kimgroup.kimflights.flight.controller;

import com.kimgroup.kimflights.flight.AirportDTO;
import com.kimgroup.kimflights.flight.service.AirportService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/airport")
@RequiredArgsConstructor
public class AirportController {
    private final AirportService airportService;

    @GetMapping
    public List<AirportDTO> findAll() {
        return airportService.findAll();
    }

    @GetMapping("/{code}")
    public AirportDTO findById(@PathVariable String code) {
        return airportService.findById(code);
    }

    @PostMapping
    public AirportDTO create(@Valid @RequestBody AirportDTO dto) {
        return airportService.create(dto);
    }

    @PutMapping("/{code}")
    public AirportDTO update(@PathVariable String code, @Valid @RequestBody AirportDTO dto) {
        return airportService.update(code, dto);
    }

    @DeleteMapping("/{code}")
    public void delete(@PathVariable String code) {
        airportService.delete(code);
    }
}

