package com.kimgroup.kimflights.service;

import com.kimgroup.kimflights.dto.AircraftDTO;
import java.util.List;

public interface AircraftService {
    List<AircraftDTO> findAll();
}
