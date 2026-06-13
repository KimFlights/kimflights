package com.kimgroup.kimflights.booking.internal;

import com.kimgroup.kimflights.booking.dto.AddressDTO;
import com.kimgroup.kimflights.booking.exception.AddressNotFoundException;
import com.kimgroup.kimflights.booking.model.Address;
import com.kimgroup.kimflights.booking.repository.AddressRepository;
import com.kimgroup.kimflights.booking.service.AddressServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressServiceImpl addressService;

    private Address address;
    private AddressDTO addressDTO;

    @BeforeEach
    void setUp() {

        address = new Address(
                "ADDR001",
                "123 Main St",
                "Toronto",
                "Ontario",
                "Canada",
                "M5V2T6"
        );

        addressDTO = AddressDTO.builder()
                .id("ADDR001")
                .street("123 Main St")
                .city("Toronto")
                .state("Ontario")
                .country("Canada")
                .postalcode("M5V2T6")
                .build();
    }

    @Test
    void shouldFindAddressById() {

        when(addressRepository.findById("ADDR001"))
                .thenReturn(Optional.of(address));

        AddressDTO result =
                addressService.findById("ADDR001");

        assertEquals("ADDR001", result.id());
        assertEquals("Toronto", result.city());
    }

    @Test
    void shouldThrowWhenAddressNotFound() {

        when(addressRepository.findById("ADDR001"))
                .thenReturn(Optional.empty());

        assertThrows(
                AddressNotFoundException.class,
                () -> addressService.findById("ADDR001")
        );
    }

    @Test
    void shouldCreateAddress() {

        when(addressRepository.save(any(Address.class)))
                .thenReturn(address);

        AddressDTO result =
                addressService.create(addressDTO);

        assertEquals("Toronto", result.city());

        verify(addressRepository).save(any(Address.class));
    }

    @Test
    void shouldUpdateAddress() {

        when(addressRepository.findById("ADDR001"))
                .thenReturn(Optional.of(address));

        when(addressRepository.save(any(Address.class)))
                .thenReturn(address);

        AddressDTO result =
                addressService.update("ADDR001", addressDTO);

        assertEquals("Toronto", result.city());

        verify(addressRepository).save(any(Address.class));
    }

    @Test
    void shouldDeleteAddress() {

        when(addressRepository.findById("ADDR001"))
                .thenReturn(Optional.of(address));

        addressService.delete("ADDR001");

        verify(addressRepository).delete(address);
    }

    @Test
    void shouldThrowWhenDeletingMissingAddress() {

        when(addressRepository.findById("ADDR001"))
                .thenReturn(Optional.empty());

        assertThrows(
                AddressNotFoundException.class,
                () -> addressService.delete("ADDR001")
        );
    }
}