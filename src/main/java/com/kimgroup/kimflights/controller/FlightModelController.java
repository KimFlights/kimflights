package com.kimgroup.kimflights.controller;

import com.kimgroup.kimflights.service.FlightModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flightmodel")
public class FlightModelController {
    @Autowired
    private FlightModelService flightModelService;

    @GetMapping
    public String findAll() {
        return flightModelService.findAll();
    }
}
