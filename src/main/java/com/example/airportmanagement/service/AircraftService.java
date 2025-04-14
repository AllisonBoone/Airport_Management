package com.example.airportmanagement.service;

// Added imports
import com.example.airportmanagement.model.Aircraft;
import com.example.airportmanagement.repository.AircraftRepository;
import org.springframework.stereotype.Service;
import com.example.airportmanagement.model.Flight;
import com.example.airportmanagement.repository.FlightRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

// Created service class for Aircraft logic.
@Service
public class AircraftService {
    private final AircraftRepository aircraftRepository;
    private final FlightRepository flightRepository;

    public AircraftService(AircraftRepository aircraftRepository, FlightRepository flightRepository) {
        this.aircraftRepository = aircraftRepository;
        this.flightRepository = flightRepository;
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

    // Get airport by aircraft.
     public List<String> getAirportsUsedByAircraft(Long aircraftId) {
        List<Flight> flights = flightRepository.findByAircraftId(aircraftId);

        Set<String> airportNames = new HashSet<>();
        for (Flight flight : flights) {
            if (flight.getDepartureAirport() != null) {
                airportNames.add(flight.getDepartureAirport().getName());
            }
            if (flight.getArrivalAirport() != null) {
                airportNames.add(flight.getArrivalAirport().getName());
            }
        }

        return new ArrayList<>(airportNames);
    }
}