package com.example.airportmanagement.service;

// Added imports.
import com.example.airportmanagement.model.Passenger;
import com.example.airportmanagement.repository.PassengerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

// Created service class for passenger logic.
@Service
public class PassengerService {
    private final PassengerRepository passengerRepository;

    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
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
}