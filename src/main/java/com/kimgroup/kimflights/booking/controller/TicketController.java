package com.kimgroup.kimflights.booking.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import com.kimgroup.kimflights.booking.dto.TicketDTO;
import com.kimgroup.kimflights.booking.service.TicketService;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    // GET ALL

    @GetMapping
    public List<TicketDTO> findAll() {
        return ticketService.findAll();
    }

    // GET BY ID

    @GetMapping("/{ticketCode}")
    public TicketDTO findById(
            @PathVariable String ticketCode) {

        return ticketService.findById(ticketCode);
    }

    // CREATE

    @PostMapping
    public TicketDTO create(
            @Valid @RequestBody TicketDTO dto) {

        return ticketService.createTicket(dto);
    }

    // UPDATE

    @PutMapping("/{ticketCode}")
    public TicketDTO update(
            @PathVariable String ticketCode,
            @Valid @RequestBody TicketDTO dto) {

        return ticketService.updateTicket(ticketCode, dto);
    }

    // DELETE

    @DeleteMapping("/{ticketCode}")
    public void delete(
            @PathVariable String ticketCode) {

        ticketService.deleteTicket(ticketCode);
    }
}