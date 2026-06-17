package com.kimgroup.kimflights.flight.controller;

import com.kimgroup.kimflights.flight.AircraftDTO;
import com.kimgroup.kimflights.flight.service.AircraftService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/aircraft")
@RequiredArgsConstructor
public class AircraftController {
    private final AircraftService aircraftService;

    @GetMapping
    public List<AircraftDTO> findAll() {
        return aircraftService.findAll();
    }

    @GetMapping("/{name}")
    public AircraftDTO findById(@PathVariable String name) {
        return aircraftService.findById(name);
    }

    @PostMapping
    public AircraftDTO create(@Valid @RequestBody AircraftDTO dto) {
        return aircraftService.create(dto);
    }

    @PutMapping("/{name}")
    public AircraftDTO update(@PathVariable String name, @Valid @RequestBody AircraftDTO dto) {
        return aircraftService.update(name, dto);
    }

    @DeleteMapping("/{name}")
    public void delete(@PathVariable String name) {
        aircraftService.delete(name);
    }

    @PostMapping("/assign")
    public String assign(@RequestParam String string) {
        aircraftService.assign(string);
        return "Assigned";
    }
}

