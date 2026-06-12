package com.kimgroup.kimflights.booking.internal;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.kimgroup.kimflights.booking.controller.LuggageController;
import com.kimgroup.kimflights.booking.dto.LuggageDTO;
import com.kimgroup.kimflights.booking.service.LuggageService;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled("Temporarily disabled")
@WebMvcTest(LuggageController.class)
class LuggageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private LuggageService luggageService;

    @Test
    void shouldReturnAllLuggage() throws Exception {
        LuggageDTO luggage1 = LuggageDTO.builder()
                .id(1)
                .weight(new BigDecimal("23.00"))
                .type("Checked Bag")
                .price(50.0)
                .build();
        LuggageDTO luggage2 = LuggageDTO.builder()
                .id(2)
                .weight(new BigDecimal("8.50"))
                .type("Cabin Bag")
                .price(0.0)
                .build();
        when(luggageService.findAll()).thenReturn(List.of(luggage1, luggage2));

        mockMvc.perform(get("/luggage"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].weight").value(23.00))
                .andExpect(jsonPath("$[0].type").value("Checked Bag"))
                .andExpect(jsonPath("$[0].price").value(50.0))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].weight").value(8.50))
                .andExpect(jsonPath("$[1].type").value("Cabin Bag"))
                .andExpect(jsonPath("$[1].price").value(0.0));
    }
}