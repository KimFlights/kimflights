package com.kimgroup.kimflights.booking.internal;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled("Temporarily disabled")
@WebMvcTest(BookingController.class)
class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookingService bookingService;

    @Test
    void shouldReturnAllBookings() throws Exception {
        BookingDTO booking1 = new BookingDTO("BK-8472", LocalDate.of(2026, 6, 10), true);
        BookingDTO booking2 = new BookingDTO("BK-9911", LocalDate.of(2026, 6, 12), false);
        when(bookingService.findAll()).thenReturn(List.of(booking1, booking2));

        mockMvc.perform(get("/booking"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].bookingReference").value("BK-8472"))
                .andExpect(jsonPath("$[0].reservedDate").value("2026-06-10"))
                .andExpect(jsonPath("$[0].bookingStatus").value(true))
                .andExpect(jsonPath("$[1].bookingReference").value("BK-9911"))
                .andExpect(jsonPath("$[1].reservedDate").value("2026-06-12"))
                .andExpect(jsonPath("$[1].bookingStatus").value(false));
    }
}
