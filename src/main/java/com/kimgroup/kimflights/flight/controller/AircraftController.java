package com.kimgroup.kimflights.flight.controller;

import com.kimgroup.kimflights.flight.dto.AircraftDTO;
import com.kimgroup.kimflights.flight.service.AircraftService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aircraft")
public class AircraftController {
    private final AircraftService aircraftService;

    public AircraftController(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }

    @GetMapping
    public List<AircraftDTO> findAll() {
        return aircraftService.findAll();
    }

    @PostMapping("/assign")
    public String assign(@RequestParam String string) {
        aircraftService.assign(string);
        return "Assigned";
    }
}
