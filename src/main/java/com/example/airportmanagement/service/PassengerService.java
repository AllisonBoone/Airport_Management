package com.example.airportmanagement.service;

// Added imports.
import com.example.airportmanagement.model.Passenger;
import com.example.airportmanagement.repository.PassengerRepository;
import org.springframework.stereotype.Service;
import com.example.airportmanagement.model.Flight;
import com.example.airportmanagement.model.Aircraft;
import com.example.airportmanagement.repository.FlightRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.Collections;

// Created service class for passenger logic.
@Service
public class PassengerService {
    private final PassengerRepository passengerRepository;
    private final FlightRepository flightRepository;

    public PassengerService(PassengerRepository passengerRepository, FlightRepository flightRepository) {
        this.passengerRepository = passengerRepository;
        this.flightRepository = flightRepository;
    }

    // Get all passengers.
    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    // Get passenger by ID.
    public Optional<Passenger> getPassengerById(Long id) {
        return passengerRepository.findById(id);
    }

    // Add passenger.
    @Transactional
    public Passenger addPassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }

    // Update passenger.
    @Transactional
    public Passenger updatePassenger(Long id, Passenger updatedPassenger) {
        return passengerRepository.findById(id).map(passenger -> {
            passenger.setFirstName(updatedPassenger.getFirstName());
            passenger.setLastName(updatedPassenger.getLastName());
            passenger.setPhoneNumber(updatedPassenger.getPhoneNumber());
            passenger.setCity(updatedPassenger.getCity());
            passenger.setAircraft(updatedPassenger.getAircraft());
            return passengerRepository.save(passenger);
        }).orElseThrow(() -> new IllegalArgumentException("Passenger not found"));
    }

    // Delete passenger.
    @Transactional
    public void deletePassenger(Long id) {
        if (!passengerRepository.existsById(id)) {
            throw new IllegalArgumentException("Passenger not found");
        }
        passengerRepository.deleteById(id);
    }

    // Get airport by passenger.
    public List<String> getAirportsUsedByPassenger(Long passengerId) {
        Optional<Passenger> passengerOpt = passengerRepository.findById(passengerId);
        if (passengerOpt.isEmpty()) {
            return Collections.emptyList();
        }

        List<Aircraft> aircraftList = passengerOpt.get().getAircraft();
        Set<String> airportNames = new HashSet<>();

        for (Aircraft aircraft : aircraftList) {
            List<Flight> flights = flightRepository.findByAircraftId(aircraft.getId());
            for (Flight flight : flights) {
                if (flight.getDepartureAirport() != null) {
                    airportNames.add(flight.getDepartureAirport().getName());
                }
                if (flight.getArrivalAirport() != null) {
                    airportNames.add(flight.getArrivalAirport().getName());
                }
            }
        }

        return new ArrayList<>(airportNames);
    }
}