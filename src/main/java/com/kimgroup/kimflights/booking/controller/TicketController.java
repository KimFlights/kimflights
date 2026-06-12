package com.kimgroup.kimflights.booking.controller;

import com.kimgroup.kimflights.booking.dto.TicketDTO;
import com.kimgroup.kimflights.booking.service.TicketService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<TicketDTO> findAll() {
        return ticketService.findAll();
    }
}
