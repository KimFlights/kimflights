package com.kimgroup.kimflights.flight.controller;

import com.kimgroup.kimflights.flight.dto.FlightDTO;
import com.kimgroup.kimflights.flight.service.FlightService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public List<FlightDTO> findAll() {
        return flightService.findAll();
    }

    @PostMapping("/update-id")
    public void updateFlightId(@RequestParam String oldId, @RequestParam String newId) {
        flightService.updateFlightId(oldId, newId);
    }
}
