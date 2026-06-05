package com.kimgroup.kimflights.service.impl;

import com.kimgroup.kimflights.repository.FlightModelRepository;
import com.kimgroup.kimflights.service.FlightModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightModelServiceImpl implements FlightModelService {
    @Autowired
    private FlightModelRepository flightModelRepository;
    @Override
    public String findAll() {
        return flightModelRepository.findAll();
    }
}
