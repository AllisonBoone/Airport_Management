package com.example.airportmanagement.service;

// Imports.
import com.example.airportmanagement.model.Flight;
import com.example.airportmanagement.repository.FlightRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

// Created service class for flight logic.
@Service
public class FlightService {
    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Optional<Flight> getFlightById(Long id) {
        return flightRepository.findById(id);
    }

    @Transactional
    public Flight addFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    @Transactional
    public Flight updateFlight(Long id, Flight updatedFlight) {
        return flightRepository.findById(id).map(flight -> {
            flight.setAircraft(updatedFlight.getAircraft());
            flight.setDepartureAirport(updatedFlight.getDepartureAirport());
            flight.setArrivalAirport(updatedFlight.getArrivalAirport());
            flight.setDepartureTime(updatedFlight.getDepartureTime());
            flight.setArrivalTime(updatedFlight.getArrivalTime());
            return flightRepository.save(flight);
        }).orElseThrow(() -> new IllegalArgumentException("Flight not found"));
    }

    @Transactional
    public void deleteFlight(Long id) {
        if (!flightRepository.existsById(id)) {
            throw new IllegalArgumentException("Flight not found");
        }
        flightRepository.deleteById(id);
    }
}
