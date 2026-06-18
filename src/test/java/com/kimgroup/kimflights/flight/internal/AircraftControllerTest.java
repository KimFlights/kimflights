package com.kimgroup.kimflights.flight.internal;

import com.kimgroup.kimflights.flight.AircraftDTO;
import com.kimgroup.kimflights.flight.controller.AircraftController;
import com.kimgroup.kimflights.flight.service.AircraftService;
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
import static org.mockito.Mockito.when;
import com.kimgroup.kimflights.security.jwt.JwtService;
import com.kimgroup.kimflights.security.CustomUserDetailsService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.kimgroup.kimflights.security.CustomUserDetailsService;

@WebMvcTest(AircraftController.class)
class AircraftControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AircraftService aircraftService;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private CustomUserDetailsService userDetailsService;

    @Test
    void shouldReturnAllAircraft() throws Exception {
        AircraftDTO aircraft = AircraftDTO.builder()
                .name("737 Max")
                .manufacturer("Boeing")
                .seatCapacity(189)
                .build();

        when(aircraftService.findAll()).thenReturn(List.of(aircraft));

        mockMvc.perform(get("/aircraft"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("737 Max"))
                .andExpect(jsonPath("$[0].manufacturer").value("Boeing"))
                .andExpect(jsonPath("$[0].seatCapacity").value(189));
    }

    @Test
    void shouldReturnAircraftByName() throws Exception {
        AircraftDTO aircraft = AircraftDTO.builder()
                .name("737 Max")
                .manufacturer("Boeing")
                .seatCapacity(189)
                .build();

        when(aircraftService.findById("737 Max")).thenReturn(aircraft);

        mockMvc.perform(get("/aircraft/737 Max"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("737 Max"))
                .andExpect(jsonPath("$.manufacturer").value("Boeing"))
                .andExpect(jsonPath("$.seatCapacity").value(189));
    }

    @Test
    void shouldCreateAircraft() throws Exception {
        AircraftDTO aircraft = AircraftDTO.builder()
                .name("737 Max")
                .manufacturer("Boeing")
                .seatCapacity(189)
                .build();

        when(aircraftService.create(any(AircraftDTO.class))).thenReturn(aircraft);

        String json = """
                {
                    "name": "737 Max",
                    "manufacturer": "Boeing",
                    "seatCapacity": 189
                }
                """;

        mockMvc.perform(post("/aircraft")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("737 Max"))
                .andExpect(jsonPath("$.seatCapacity").value(189));
    }

    @Test
    void shouldUpdateAircraft() throws Exception {
        AircraftDTO aircraft = AircraftDTO.builder()
                .name("737 Max")
                .manufacturer("Boeing")
                .seatCapacity(200)
                .build();

        when(aircraftService.update(eq("737 Max"), any(AircraftDTO.class))).thenReturn(aircraft);

        String json = """
                {
                    "name": "737 Max",
                    "manufacturer": "Boeing",
                    "seatCapacity": 200
                }
                """;

        mockMvc.perform(put("/aircraft/737 Max")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("737 Max"))
                .andExpect(jsonPath("$.seatCapacity").value(200));
    }

    @Test
    void shouldDeleteAircraft() throws Exception {
        doNothing().when(aircraftService).delete("737 Max");

        mockMvc.perform(delete("/aircraft/737 Max"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnBadRequestWhenCreatingAircraftWithInvalidData() throws Exception {
        String json = """
                {
                    "name": "",
                    "manufacturer": "",
                    "seatCapacity": -10
                }
                """;

        mockMvc.perform(post("/aircraft")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").exists())
                .andExpect(jsonPath("$.manufacturer").exists())
                .andExpect(jsonPath("$.seatCapacity").exists());
    }
}
