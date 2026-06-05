package com.kimgroup.kimflights.service.impl;

import com.kimgroup.kimflights.domain.Aircraft;
import com.kimgroup.kimflights.dto.AircraftDTO;
import com.kimgroup.kimflights.repository.AircraftRepository;
import com.kimgroup.kimflights.service.AircraftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AircraftServiceImpl implements AircraftService {
    @Autowired
    private AircraftRepository aircraftRepository;

    @Override
    public List<AircraftDTO> findAll() {
        return aircraftRepository.findAll().stream()
            .map(aircraft -> new AircraftDTO(
                aircraft.getName(),
                aircraft.getManufacturer(),
                aircraft.getSeatCapacity()
            ))
            .toList();
    }
}
