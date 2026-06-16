package com.kimgroup.kimflights.flight.controller;

import com.kimgroup.kimflights.flight.FlightRouteDTO;
import com.kimgroup.kimflights.flight.service.FlightRouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/flight-route")
@RequiredArgsConstructor
public class FlightRouteController {
    private final FlightRouteService flightRouteService;

    @GetMapping
    public List<FlightRouteDTO> findAll() {
        return flightRouteService.findAll();
    }

    @GetMapping("/{flightCode}")
    public FlightRouteDTO findById(@PathVariable String flightCode) {
        return flightRouteService.findById(flightCode);
    }

    @PostMapping
    public FlightRouteDTO create(@Valid @RequestBody FlightRouteDTO dto) {
        return flightRouteService.create(dto);
    }

    @PutMapping("/{flightCode}")
    public FlightRouteDTO update(
            @PathVariable String flightCode,
            @Valid @RequestBody FlightRouteDTO dto
    ) {
        return flightRouteService.update(flightCode, dto);
    }

    @DeleteMapping("/{flightCode}")
    public void delete(@PathVariable String flightCode) {
        flightRouteService.delete(flightCode);
    }
}
