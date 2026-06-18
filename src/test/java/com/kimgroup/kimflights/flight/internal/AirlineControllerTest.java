package com.kimgroup.kimflights.flight.internal;

import com.kimgroup.kimflights.flight.AirlineDTO;
import com.kimgroup.kimflights.flight.controller.AirlineController;
import com.kimgroup.kimflights.flight.service.AirlineService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import com.kimgroup.kimflights.security.jwt.JwtService;
import com.kimgroup.kimflights.security.CustomUserDetailsService;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AirlineController.class)
class AirlineControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AirlineService airlineService;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private CustomUserDetailsService userDetailsService;

    @Test
    void shouldReturnAllAirlines() throws Exception {
        AirlineDTO airline = AirlineDTO.builder()
                .id(1)
                .code("DL")
                .name("Delta Air Lines")
                .build();

        when(airlineService.findAll()).thenReturn(List.of(airline));

        mockMvc.perform(get("/airline"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].code").value("DL"))
                .andExpect(jsonPath("$[0].name").value("Delta Air Lines"));
    }

    @Test
    void shouldReturnAirlineById() throws Exception {
        AirlineDTO airline = AirlineDTO.builder()
                .id(1)
                .code("DL")
                .name("Delta Air Lines")
                .build();

        when(airlineService.findById(1)).thenReturn(airline);

        mockMvc.perform(get("/airline/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.code").value("DL"))
                .andExpect(jsonPath("$.name").value("Delta Air Lines"));
    }

    @Test
    void shouldCreateAirline() throws Exception {
        AirlineDTO airline = AirlineDTO.builder()
                .id(1)
                .code("DL")
                .name("Delta Air Lines")
                .build();

        when(airlineService.create(any(AirlineDTO.class))).thenReturn(airline);

        String json = """
                {
                    "code": "DL",
                    "name": "Delta Air Lines"
                }
                """;

        mockMvc.perform(post("/airline")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.code").value("DL"));
    }

    @Test
    void shouldUpdateAirline() throws Exception {
        AirlineDTO airline = AirlineDTO.builder()
                .id(1)
                .code("DL")
                .name("Delta Air Lines Inc")
                .build();

        when(airlineService.update(eq(1), any(AirlineDTO.class))).thenReturn(airline);

        String json = """
                {
                    "id": 1,
                    "code": "DL",
                    "name": "Delta Air Lines Inc"
                }
                """;

        mockMvc.perform(put("/airline/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Delta Air Lines Inc"));
    }

    @Test
    void shouldDeleteAirline() throws Exception {
        doNothing().when(airlineService).delete(1);

        mockMvc.perform(delete("/airline/1"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnBadRequestWhenCreatingAirlineWithInvalidData() throws Exception {
        String json = """
                {
                    "code": "",
                    "name": ""
                }
                """;

        mockMvc.perform(post("/airline")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").exists())
                .andExpect(jsonPath("$.name").exists());
    }
}
