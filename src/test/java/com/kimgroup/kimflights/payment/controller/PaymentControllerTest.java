package com.kimgroup.kimflights.payment.controller;

import com.kimgroup.kimflights.payment.dto.PaymentRequestDTO;
import com.kimgroup.kimflights.payment.dto.TransactionDTO;
import com.kimgroup.kimflights.payment.service.PaymentService;
import com.kimgroup.kimflights.security.CustomUserDetailsService;
import com.kimgroup.kimflights.security.jwt.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PaymentController.class)
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PaymentService paymentService;

    @MockitoBean
    private JwtService jwtService;

    @MockitoBean
    private CustomUserDetailsService userDetailsService;

    @Test
    void shouldCheckCardBrand() throws Exception {
        when(paymentService.getCardBrand("411111")).thenReturn("VISA");

        mockMvc.perform(get("/payment/brand/411111"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("VISA"));
    }

    @Test
    void shouldProcessPayment() throws Exception {
        TransactionDTO mockTransaction = TransactionDTO.builder()
                .id(1L)
                .amount(BigDecimal.valueOf(100.0))
                .status("SUCCESS")
                .brand("VISA")
                .build();
        
        when(paymentService.processPayment(any(PaymentRequestDTO.class))).thenReturn(mockTransaction);

        String json = """
                {
                    "cardNumber": "4111111111111111",
                    "invoiceId": 1
                }
                """;

        mockMvc.perform(post("/payment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(1));
    }
}
