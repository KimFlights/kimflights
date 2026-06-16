package com.kimgroup.kimflights.flight.internal;

import com.kimgroup.kimflights.flight.FlightDTO;
import com.kimgroup.kimflights.flight.controller.FlightController;
import com.kimgroup.kimflights.flight.models.FlightStatus;
import com.kimgroup.kimflights.flight.service.FlightService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FlightController.class)
class FlightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FlightService flightService;

    @Test
    void shouldReturnAllFlights() throws Exception {
        FlightDTO flight1 = FlightDTO.builder()
                .id("FL-123")
                .departureDate(LocalDateTime.of(2026, Month.JUNE, 11, 8, 0))
                .arrivalDate(LocalDateTime.of(2026, Month.JUNE, 11, 10, 30))
                .distance(850)
                .estimatedTimeInMinutes(150)
                .flightStatus(FlightStatus.SCHEDULED)
                .aircraftName("737 Max")
                .airlineName("Delta Air Lines")
                .originAirportCode("JFK")
                .destinationAirportCode("LAX")
                .build();

        when(flightService.findAll()).thenReturn(List.of(flight1));

        mockMvc.perform(get("/flight"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value("FL-123"))
                .andExpect(jsonPath("$[0].distance").value(850))
                .andExpect(jsonPath("$[0].flightStatus").value("SCHEDULED"));
    }

    @Test
    void shouldReturnFlightById() throws Exception {
        FlightDTO flight = FlightDTO.builder()
                .id("FL-123")
                .departureDate(LocalDateTime.of(2026, Month.JUNE, 11, 8, 0))
                .arrivalDate(LocalDateTime.of(2026, Month.JUNE, 11, 10, 30))
                .distance(850)
                .estimatedTimeInMinutes(150)
                .flightStatus(FlightStatus.SCHEDULED)
                .aircraftName("737 Max")
                .airlineName("Delta Air Lines")
                .originAirportCode("JFK")
                .destinationAirportCode("LAX")
                .build();

        when(flightService.findById("FL-123")).thenReturn(flight);

        mockMvc.perform(get("/flight/FL-123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("FL-123"))
                .andExpect(jsonPath("$.distance").value(850))
                .andExpect(jsonPath("$.flightStatus").value("SCHEDULED"));
    }

    @Test
    void shouldCreateFlight() throws Exception {
        FlightDTO flight = FlightDTO.builder()
                .id("FL-123")
                .departureDate(LocalDateTime.of(2026, Month.JUNE, 11, 8, 0))
                .arrivalDate(LocalDateTime.of(2026, Month.JUNE, 11, 10, 30))
                .distance(850)
                .estimatedTimeInMinutes(150)
                .flightStatus(FlightStatus.SCHEDULED)
                .aircraftName("737 Max")
                .airlineName("Delta Air Lines")
                .originAirportCode("JFK")
                .destinationAirportCode("LAX")
                .build();

        when(flightService.create(any(FlightDTO.class))).thenReturn(flight);

        String json = """
                {
                    "id": "FL-123",
                    "departureDate": "2026-06-11T08:00:00",
                    "arrivalDate": "2026-06-11T10:30:00",
                    "distance": 850,
                    "estimatedTimeInMinutes": 150,
                    "flightStatus": "SCHEDULED",
                    "aircraftName": "737 Max",
                    "airlineName": "Delta Air Lines",
                    "originAirportCode": "JFK",
                    "destinationAirportCode": "LAX"
                }
                """;

        mockMvc.perform(post("/flight")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("FL-123"))
                .andExpect(jsonPath("$.distance").value(850));
    }

    @Test
    void shouldUpdateFlight() throws Exception {
        FlightDTO flight = FlightDTO.builder()
                .id("FL-123")
                .departureDate(LocalDateTime.of(2026, Month.JUNE, 11, 8, 0))
                .arrivalDate(LocalDateTime.of(2026, Month.JUNE, 11, 10, 30))
                .distance(900)
                .estimatedTimeInMinutes(160)
                .flightStatus(FlightStatus.DELAYED)
                .aircraftName("737 Max")
                .airlineName("Delta Air Lines")
                .originAirportCode("JFK")
                .destinationAirportCode("LAX")
                .build();

        when(flightService.update(eq("FL-123"), any(FlightDTO.class))).thenReturn(flight);

        String json = """
                {
                    "id": "FL-123",
                    "departureDate": "2026-06-11T08:00:00",
                    "arrivalDate": "2026-06-11T10:30:00",
                    "distance": 900,
                    "estimatedTimeInMinutes": 160,
                    "flightStatus": "DELAYED",
                    "aircraftName": "737 Max",
                    "airlineName": "Delta Air Lines",
                    "originAirportCode": "JFK",
                    "destinationAirportCode": "LAX"
                }
                """;

        mockMvc.perform(put("/flight/FL-123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("FL-123"))
                .andExpect(jsonPath("$.distance").value(900))
                .andExpect(jsonPath("$.flightStatus").value("DELAYED"));
    }

    @Test
    void shouldDeleteFlight() throws Exception {
        doNothing().when(flightService).delete("FL-123");

        mockMvc.perform(delete("/flight/FL-123"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnBadRequestWhenCreatingFlightWithInvalidData() throws Exception {
        String json = """
                {
                    "id": "",
                    "departureDate": null,
                    "arrivalDate": "2026-06-11T10:30:00",
                    "distance": -10,
                    "estimatedTimeInMinutes": 0,
                    "flightStatus": null,
                    "airlineName": "",
                    "originAirportCode": "",
                    "destinationAirportCode": ""
                }
                """;

        mockMvc.perform(post("/flight")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.departureDate").exists())
                .andExpect(jsonPath("$.distance").exists())
                .andExpect(jsonPath("$.estimatedTimeInMinutes").exists())
                .andExpect(jsonPath("$.flightStatus").exists())
                .andExpect(jsonPath("$.airlineName").exists())
                .andExpect(jsonPath("$.originAirportCode").exists())
                .andExpect(jsonPath("$.destinationAirportCode").exists());
    }
}

