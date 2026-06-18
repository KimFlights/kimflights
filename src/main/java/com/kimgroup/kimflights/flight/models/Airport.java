package com.kimgroup.kimflights.flight.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Airport {
    @Id
    private String code;
    private String name;
    private String addressId;
}
