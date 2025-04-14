package com.example.airportmanagement.controller;

// Added imports.
import com.example.airportmanagement.model.Aircraft;
import com.example.airportmanagement.model.Passenger;
import com.example.airportmanagement.service.PassengerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
// Created controller for passenger api.
@RestController
@RequestMapping("/api/passengers")
public class PassengerController {
    private final PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    // Get all passengers.
    @GetMapping
    public ResponseEntity<List<Passenger>> getAllPassengers() {
        return ResponseEntity.ok(passengerService.getAllPassengers());
    }

    // Get passengers by ID
    @GetMapping("/{id}")
    public ResponseEntity<Passenger> getPassengerById(@PathVariable Long id) {
        Optional<Passenger> passenger = passengerService.getPassengerById(id);
        return passenger.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Add new passenger.
    @PostMapping
    public ResponseEntity<Passenger> addPassenger(@RequestBody Passenger passenger) {
        return new ResponseEntity<>(passengerService.addPassenger(passenger), HttpStatus.CREATED);
    }

    // Update passenger.
    @PutMapping("/{id}")
    public ResponseEntity<Passenger> updatePassenger(@PathVariable Long id, @RequestBody Passenger passenger) {
        return ResponseEntity.ok(passengerService.updatePassenger(id, passenger));
    }

    // Delete existing passenger.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePassenger(@PathVariable Long id) {
        passengerService.deletePassenger(id);
        return ResponseEntity.noContent().build();
    }

    // Get all aircraft passenger been on.
    @GetMapping("/{id}/aircraft")
    public ResponseEntity<List<Aircraft>> getAircraftForPassenger(@PathVariable Long id) {
        return passengerService.getPassengerById(id)
                .map(passenger -> ResponseEntity.ok(passenger.getAircraft()))
                .orElse(ResponseEntity.notFound().build());
    }

    // Get airport by passenger.
    @GetMapping("/{id}/airports")
    public ResponseEntity<List<String>> getAirportsUsedByPassenger(@PathVariable Long id) {
        List<String> airports = passengerService.getAirportsUsedByPassenger(id);
        return ResponseEntity.ok(airports);
    }
}
