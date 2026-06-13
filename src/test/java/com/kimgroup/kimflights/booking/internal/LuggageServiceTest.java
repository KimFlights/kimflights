package com.kimgroup.kimflights.booking.internal;

import com.kimgroup.kimflights.booking.dto.LuggageDTO;
import com.kimgroup.kimflights.booking.exception.LuggageNotFoundException;
import com.kimgroup.kimflights.booking.model.Luggage;
import com.kimgroup.kimflights.booking.repository.LuggageRepository;
import com.kimgroup.kimflights.booking.service.LuggageService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LuggageServiceTest {

    @Mock
    private LuggageRepository luggageRepository;

    @InjectMocks
    private LuggageService luggageService;

    private Luggage luggage;
    private LuggageDTO luggageDTO;

    @BeforeEach
    void setUp() {

        luggage = new Luggage(
                1,
                new BigDecimal("23.00"),
                "Checked Bag",
                50.0
        );

        luggageDTO = LuggageDTO.builder()
                .id(1)
                .weight(new BigDecimal("23.00"))
                .type("Checked Bag")
                .price(50.0)
                .build();
    }

    @Test
    void shouldFindLuggageById() {

        when(luggageRepository.findById(1))
                .thenReturn(Optional.of(luggage));

        LuggageDTO result =
                luggageService.findById(1);

        assertEquals(1, result.id());
    }

    @Test
    void shouldThrowWhenLuggageMissing() {

        when(luggageRepository.findById(1))
                .thenReturn(Optional.empty());

        assertThrows(
                LuggageNotFoundException.class,
                () -> luggageService.findById(1)
        );
    }

    @Test
    void shouldCreateLuggage() {

        when(luggageRepository.save(any()))
                .thenReturn(luggage);

        LuggageDTO result =
                luggageService.createLuggage(luggageDTO);

        assertEquals("Checked Bag",
                result.type());

        verify(luggageRepository)
                .save(any());
    }

    @Test
    void shouldDeleteLuggage() {

        when(luggageRepository.existsById(1))
                .thenReturn(true);

        luggageService.deleteLuggage(1);

        verify(luggageRepository)
                .deleteById(1);
    }
}