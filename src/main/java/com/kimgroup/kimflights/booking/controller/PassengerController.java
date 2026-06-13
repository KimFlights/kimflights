package com.kimgroup.kimflights.booking.controller;

import com.kimgroup.kimflights.booking.dto.PassengerDTO;
import com.kimgroup.kimflights.booking.service.PassengerService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passenger")
public class PassengerController {

    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    // GET ALL

    @GetMapping
    public List<PassengerDTO> findAll() {
        return passengerService.findAll();
    }

    // GET BY ID

    @GetMapping("/{id}")
    public PassengerDTO findById(@PathVariable String id) {
        return passengerService.findById(id);
    }

    // CREATE

    @PostMapping
    public PassengerDTO create(
            @Valid @RequestBody PassengerDTO dto) {

        return passengerService.createPassenger(dto);
    }

    // UPDATE

    @PutMapping("/{id}")
    public PassengerDTO update(
            @PathVariable String id,
            @Valid @RequestBody PassengerDTO dto) {

        return passengerService.updatePassenger(id, dto);
    }

    // DELETE

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        passengerService.deletePassenger(id);
    }
}