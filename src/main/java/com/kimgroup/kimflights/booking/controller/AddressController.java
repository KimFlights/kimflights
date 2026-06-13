package com.kimgroup.kimflights.booking.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kimgroup.kimflights.booking.dto.AddressDTO;
import com.kimgroup.kimflights.booking.service.AddressService;

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
        return addressService.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Address not found: " + id));
    }

    @PostMapping
    public AddressDTO create(@RequestBody AddressDTO dto) {
        return addressService.create(dto);
    }

    @PutMapping("/{id}")
    public AddressDTO update(
            @PathVariable String id,
            @RequestBody AddressDTO dto) {

        return addressService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        addressService.delete(id);
    }
}