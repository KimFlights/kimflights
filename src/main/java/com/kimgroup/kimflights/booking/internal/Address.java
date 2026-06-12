package com.kimgroup.kimflights.booking.internal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    private String id;
    private String street;
    private String city;
    private String state;
    private String country;
    private String postalcode;
}
