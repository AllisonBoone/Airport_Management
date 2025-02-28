package com.example.airportmanagement.service;
 
// Added imports.
import com.example.airportmanagement.model.Airport;
import com.example.airportmanagement.repository.AirportRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

// Created service class for airport logic.
@Service
public class AirportService {
    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    // Get all airports.
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    // Get airport by ID.
    public Optional<Airport> getAirportById(Long id) {
        return airportRepository.findById(id);
    }

    // Add airport.
    @Transactional
    public Airport addAirport(Airport airport) {
        return airportRepository.save(airport);
    }

    // Update airport.
    @Transactional
    public Airport updateAirport(Long id, Airport updatedAirport) {
        return airportRepository.findById(id).map(airport -> {
            airport.setName(updatedAirport.getName());
            airport.setCode(updatedAirport.getCode());
            airport.setCity(updatedAirport.getCity());
            return airportRepository.save(airport);
        }).orElseThrow(() -> new IllegalArgumentException("Airport not found"));
    }

    // Delete airport.
    @Transactional
    public void deleteAirport(Long id) {
        if (!airportRepository.existsById(id)) {
            throw new IllegalArgumentException("Airport not found");
        }
        airportRepository.deleteById(id);
    }
}