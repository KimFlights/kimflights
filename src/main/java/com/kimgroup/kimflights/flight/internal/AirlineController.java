package com.kimgroup.kimflights.flight.internal;

import com.kimgroup.kimflights.flight.AirlineDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/airline")
class AirlineController {
    private final AirlineService airlineService;

    public AirlineController(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    @GetMapping
    public List<AirlineDTO> findAll() {
        return airlineService.findAll();
    }
}
