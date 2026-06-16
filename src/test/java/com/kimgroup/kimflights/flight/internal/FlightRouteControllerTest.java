package com.kimgroup.kimflights.flight.internal;

import com.kimgroup.kimflights.flight.FlightRouteDTO;
import com.kimgroup.kimflights.flight.controller.FlightRouteController;
import com.kimgroup.kimflights.flight.service.FlightRouteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FlightRouteController.class)
class FlightRouteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FlightRouteService flightRouteService;

    @Test
    void shouldReturnAllFlightRoutes() throws Exception {
        FlightRouteDTO route = FlightRouteDTO.builder()
                .flightCode("AA100")
                .departureTime(LocalTime.of(8, 0))
                .arrivalTime(LocalTime.of(10, 30))
                .airlineName("American Airlines")
                .originAirportCode("JFK")
                .destinationAirportCode("LAX")
                .build();

        when(flightRouteService.findAll()).thenReturn(List.of(route));

        mockMvc.perform(get("/flight-route"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].flightCode").value("AA100"))
                .andExpect(jsonPath("$[0].airlineName").value("American Airlines"))
                .andExpect(jsonPath("$[0].originAirportCode").value("JFK"))
                .andExpect(jsonPath("$[0].destinationAirportCode").value("LAX"));
    }

    @Test
    void shouldReturnFlightRouteByCode() throws Exception {
        FlightRouteDTO route = FlightRouteDTO.builder()
                .flightCode("AA100")
                .departureTime(LocalTime.of(8, 0))
                .arrivalTime(LocalTime.of(10, 30))
                .airlineName("American Airlines")
                .originAirportCode("JFK")
                .destinationAirportCode("LAX")
                .build();

        when(flightRouteService.findById("AA100")).thenReturn(route);

        mockMvc.perform(get("/flight-route/AA100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.flightCode").value("AA100"))
                .andExpect(jsonPath("$.airlineName").value("American Airlines"))
                .andExpect(jsonPath("$.originAirportCode").value("JFK"))
                .andExpect(jsonPath("$.destinationAirportCode").value("LAX"));
    }

    @Test
    void shouldCreateFlightRoute() throws Exception {
        FlightRouteDTO route = FlightRouteDTO.builder()
                .flightCode("AA100")
                .departureTime(LocalTime.of(8, 0))
                .arrivalTime(LocalTime.of(10, 30))
                .airlineName("American Airlines")
                .originAirportCode("JFK")
                .destinationAirportCode("LAX")
                .build();

        when(flightRouteService.create(any(FlightRouteDTO.class))).thenReturn(route);

        String json = """
                {
                    "flightCode": "AA100",
                    "departureTime": "08:00:00",
                    "arrivalTime": "10:30:00",
                    "airlineName": "American Airlines",
                    "originAirportCode": "JFK",
                    "destinationAirportCode": "LAX"
                }
                """;

        mockMvc.perform(post("/flight-route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.flightCode").value("AA100"))
                .andExpect(jsonPath("$.airlineName").value("American Airlines"));
    }

    @Test
    void shouldUpdateFlightRoute() throws Exception {
        FlightRouteDTO route = FlightRouteDTO.builder()
                .flightCode("AA100")
                .departureTime(LocalTime.of(8, 15))
                .arrivalTime(LocalTime.of(10, 45))
                .airlineName("American Airlines")
                .originAirportCode("JFK")
                .destinationAirportCode("LAX")
                .build();

        when(flightRouteService.update(eq("AA100"), any(FlightRouteDTO.class))).thenReturn(route);

        String json = """
                {
                    "flightCode": "AA100",
                    "departureTime": "08:15:00",
                    "arrivalTime": "10:45:00",
                    "airlineName": "American Airlines",
                    "originAirportCode": "JFK",
                    "destinationAirportCode": "LAX"
                }
                """;

        mockMvc.perform(put("/flight-route/AA100")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.flightCode").value("AA100"))
                .andExpect(jsonPath("$.departureTime").value("08:15:00"));
    }

    @Test
    void shouldDeleteFlightRoute() throws Exception {
        doNothing().when(flightRouteService).delete("AA100");

        mockMvc.perform(delete("/flight-route/AA100"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnBadRequestWhenCreatingFlightRouteWithInvalidData() throws Exception {
        String json = """
                {
                    "flightCode": "",
                    "departureTime": null,
                    "arrivalTime": null,
                    "airlineName": "",
                    "originAirportCode": "",
                    "destinationAirportCode": ""
                }
                """;

        mockMvc.perform(post("/flight-route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.flightCode").exists())
                .andExpect(jsonPath("$.departureTime").exists())
                .andExpect(jsonPath("$.arrivalTime").exists())
                .andExpect(jsonPath("$.airlineName").exists())
                .andExpect(jsonPath("$.originAirportCode").exists())
                .andExpect(jsonPath("$.destinationAirportCode").exists());
    }
}
