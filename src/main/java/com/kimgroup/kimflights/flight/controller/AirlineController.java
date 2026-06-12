package com.kimgroup.kimflights.flight.controller;

import com.kimgroup.kimflights.flight.dto.AirlineDTO;
import com.kimgroup.kimflights.flight.service.AirlineService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/airline")
public class AirlineController {
    private final AirlineService airlineService;

    public AirlineController(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    @GetMapping
    public List<AirlineDTO> findAll() {
        return airlineService.findAll();
    }
}
