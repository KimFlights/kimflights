package com.kimgroup.kimflights.booking.controller;

import com.kimgroup.kimflights.booking.dto.PassengerDTO;
import com.kimgroup.kimflights.booking.service.PassengerService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/passenger")
public class PassengerController {
    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping
    public List<PassengerDTO> findAll() {
        return passengerService.findAll();
    }
}
