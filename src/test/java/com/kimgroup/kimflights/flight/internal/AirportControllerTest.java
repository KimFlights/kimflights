package com.kimgroup.kimflights.flight.internal;

import com.kimgroup.kimflights.flight.AirportDTO;
import com.kimgroup.kimflights.flight.controller.AirportController;
import com.kimgroup.kimflights.flight.service.AirportService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AirportController.class)
class AirportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AirportService airportService;

    @Test
    void shouldReturnAllAirports() throws Exception {
        AirportDTO airport = AirportDTO.builder()
                .code("JFK")
                .addressId("AD-001")
                .build();

        when(airportService.findAll()).thenReturn(List.of(airport));

        mockMvc.perform(get("/airport"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].code").value("JFK"))
                .andExpect(jsonPath("$[0].addressId").value("AD-001"));
    }

    @Test
    void shouldReturnAirportByCode() throws Exception {
        AirportDTO airport = AirportDTO.builder()
                .code("JFK")
                .addressId("AD-001")
                .build();

        when(airportService.findById("JFK")).thenReturn(airport);

        mockMvc.perform(get("/airport/JFK"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("JFK"))
                .andExpect(jsonPath("$.addressId").value("AD-001"));
    }

    @Test
    void shouldCreateAirport() throws Exception {
        AirportDTO airport = AirportDTO.builder()
                .code("JFK")
                .addressId("AD-001")
                .build();

        when(airportService.create(any(AirportDTO.class))).thenReturn(airport);

        String json = """
                {
                    "code": "JFK",
                    "addressId": "AD-001"
                }
                """;

        mockMvc.perform(post("/airport")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("JFK"))
                .andExpect(jsonPath("$.addressId").value("AD-001"));
    }

    @Test
    void shouldUpdateAirport() throws Exception {
        AirportDTO airport = AirportDTO.builder()
                .code("JFK")
                .addressId("AD-002")
                .build();

        when(airportService.update(eq("JFK"), any(AirportDTO.class))).thenReturn(airport);

        String json = """
                {
                    "code": "JFK",
                    "addressId": "AD-002"
                }
                """;

        mockMvc.perform(put("/airport/JFK")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("JFK"))
                .andExpect(jsonPath("$.addressId").value("AD-002"));
    }

    @Test
    void shouldDeleteAirport() throws Exception {
        doNothing().when(airportService).delete("JFK");

        mockMvc.perform(delete("/airport/JFK"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnBadRequestWhenCreatingAirportWithInvalidData() throws Exception {
        String json = """
                {
                    "code": "",
                    "addressId": ""
                }
                """;

        mockMvc.perform(post("/airport")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").exists())
                .andExpect(jsonPath("$.addressId").exists());
    }
}
