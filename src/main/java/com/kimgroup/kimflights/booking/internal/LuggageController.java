package com.kimgroup.kimflights.booking.internal;

import com.kimgroup.kimflights.booking.LuggageDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/luggage")
public class LuggageController {
    private final LuggageService luggageService;

    public LuggageController(LuggageService luggageService) {
        this.luggageService = luggageService;
    }

    @GetMapping
    public List<LuggageDTO> findAll() {
        return luggageService.findAll();
    }
}
