package com.kimgroup.kimflights.flight.controller;

import com.kimgroup.kimflights.flight.dto.AirportDTO;
import com.kimgroup.kimflights.flight.service.AirportService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/airport")
public class AirportController {
    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping
    public List<AirportDTO> findAll() {
        return airportService.findAll();
    }
}
