package com.kimgroup.kimflights.flight.controller;

import com.kimgroup.kimflights.flight.FlightDTO;
import com.kimgroup.kimflights.flight.service.FlightService;

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
@RequestMapping("/flight")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;

    @GetMapping
    public List<FlightDTO> findAll() {
        return flightService.findAll();
    }

    @GetMapping("/search")
    public List<FlightDTO> search(
            @RequestParam String origin,
            @RequestParam String destination,
            @RequestParam String date,
            @RequestParam(defaultValue = "1") int passengers) {
        // passengers is received for future seat-availability filtering
        return flightService.searchFlights(origin, destination, date);
    }

    @GetMapping("/{id}")
    public FlightDTO findById(@PathVariable String id) {
        return flightService.findById(id);
    }

    @PostMapping
    public FlightDTO create(@Valid @RequestBody FlightDTO dto) {
        return flightService.create(dto);
    }

    @PutMapping("/{id}")
    public FlightDTO update(@PathVariable String id, @Valid @RequestBody FlightDTO dto) {
        return flightService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        flightService.delete(id);
    }

    @PostMapping("/update-id")
    public void updateFlightId(@RequestParam String oldId, @RequestParam String newId) {
        flightService.updateFlightId(oldId, newId);
    }
}
