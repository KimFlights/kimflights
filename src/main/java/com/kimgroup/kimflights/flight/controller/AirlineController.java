package com.kimgroup.kimflights.flight.controller;

import com.kimgroup.kimflights.flight.AirlineDTO;
import com.kimgroup.kimflights.flight.service.AirlineService;

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
@RequestMapping("/airline")
@RequiredArgsConstructor
public class AirlineController {
    private final AirlineService airlineService;

    @GetMapping
    public List<AirlineDTO> findAll() {
        return airlineService.findAll();
    }

    @GetMapping("/{id}")
    public AirlineDTO findById(@PathVariable Integer id) {
        return airlineService.findById(id);
    }

    @PostMapping
    public AirlineDTO create(@Valid @RequestBody AirlineDTO dto) {
        return airlineService.create(dto);
    }

    @PutMapping("/{id}")
    public AirlineDTO update(@PathVariable Integer id, @Valid @RequestBody AirlineDTO dto) {
        return airlineService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        airlineService.delete(id);
    }
}

