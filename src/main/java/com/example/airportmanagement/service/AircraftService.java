package com.example.airportmanagement.service;

// Added imports
import com.example.airportmanagement.model.Aircraft;
import com.example.airportmanagement.repository.AircraftRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
// Created service class for Aircraft logic.
@Service
public class AircraftService {
    private final AircraftRepository aircraftRepository;

    public AircraftService(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }

    // Get all aircraft
    public List<Aircraft> getAllAircraft() {
        return aircraftRepository.findAll();
    }

    // Get aircraft by ID.
    public Optional<Aircraft> getAircraftById(Long id) {
        return aircraftRepository.findById(id);
    }

    // Add aircraft.
    @Transactional
    public Aircraft addAircraft(Aircraft aircraft) {
        return aircraftRepository.save(aircraft);
    }

    // Update aircraft.
    @Transactional
    public Aircraft updateAircraft(Long id, Aircraft updatedAircraft) {
        return aircraftRepository.findById(id).map(aircraft -> {
            aircraft.setType(updatedAircraft.getType());
            aircraft.setAirlineName(updatedAircraft.getAirlineName());
            aircraft.setNumberOfPassengers(updatedAircraft.getNumberOfPassengers());
            return aircraftRepository.save(aircraft);
        }).orElseThrow(() -> new IllegalArgumentException("Aircraft not found"));
    }

    // Delete aircraft.
    @Transactional
    public void deleteAircraft(Long id) {
        if (!aircraftRepository.existsById(id)) {
            throw new IllegalArgumentException("Aircraft not found");
        }
        aircraftRepository.deleteById(id);
    }
}