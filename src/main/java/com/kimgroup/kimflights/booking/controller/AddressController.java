package com.kimgroup.kimflights.booking.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.kimgroup.kimflights.booking.dto.AddressDTO;
import com.kimgroup.kimflights.booking.service.AddressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public List<AddressDTO> findAll() {
        return addressService.findAll();
    }

    @GetMapping("/{id}")
    public AddressDTO findById(@PathVariable String id) {
        return addressService.findById(id);
    }

    @PostMapping
    public AddressDTO create(
            @Valid @RequestBody AddressDTO dto) {

        return addressService.create(dto);
    }

    @PutMapping("/{id}")
    public AddressDTO update(
            @PathVariable String id,
            @Valid @RequestBody AddressDTO dto) {

        return addressService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        addressService.delete(id);
    }
}