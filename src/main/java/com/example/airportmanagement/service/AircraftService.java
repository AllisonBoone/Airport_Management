package com.example.airportmanagement.service;

import com.example.airportmanagement.dto.AircraftDto;
import com.example.airportmanagement.model.Aircraft;
import com.example.airportmanagement.repository.AircraftRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AircraftService {

    private final AircraftRepository aircraftRepository;

    public AircraftService(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }

    public List<AircraftDto> getAllAircraft() {
        return aircraftRepository.findAll().stream()
                .map(a -> new AircraftDto(
                      a.getId(), a.getType(), a.getAirlineName(), a.getNumberOfPassengers()))
                .collect(Collectors.toList());
    }

    public Optional<AircraftDto> getAircraftById(Long id) {
        return aircraftRepository.findById(id)
                .map(a -> new AircraftDto(
                      a.getId(), a.getType(), a.getAirlineName(), a.getNumberOfPassengers()));
    }

    public AircraftDto addAircraft(AircraftDto dto) {
        Aircraft a = new Aircraft();
        a.setType(dto.getModel());
        a.setAirlineName(dto.getManufacturer());
        a.setNumberOfPassengers(dto.getCapacity());
        Aircraft saved = aircraftRepository.save(a);
        return new AircraftDto(saved.getId(), saved.getType(), saved.getAirlineName(), saved.getNumberOfPassengers());
    }

    public AircraftDto updateAircraft(Long id, AircraftDto dto) {
        Aircraft updated = aircraftRepository.findById(id)
            .map(e -> {
                e.setType(dto.getModel());
                e.setAirlineName(dto.getManufacturer());
                e.setNumberOfPassengers(dto.getCapacity());
                return aircraftRepository.save(e);
            })
            .orElseThrow(() -> new EntityNotFoundException("Aircraft not found: " + id));
        return new AircraftDto(updated.getId(), updated.getType(), updated.getAirlineName(), updated.getNumberOfPassengers());
    }

    public void deleteAircraft(Long id) {
        aircraftRepository.deleteById(id);
    }
}
