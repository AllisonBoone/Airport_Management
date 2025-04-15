package com.example.airportmanagement.controller;

// Imports.
import com.example.airportmanagement.model.Flight;
import com.example.airportmanagement.service.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

// Created controller for flight api.
@RestController
@RequestMapping("/api/flights")
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public ResponseEntity<List<Flight>> getAllFlights() {
        return ResponseEntity.ok(flightService.getAllFlights());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable("id") Long id) {
        Optional<Flight> flight = flightService.getFlightById(id);
        return flight.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Flight> addFlight(@RequestBody Flight flight) {
        return new ResponseEntity<>(flightService.addFlight(flight), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Flight> updateFlight(@PathVariable("id") Long id, @RequestBody Flight flight) {
        return ResponseEntity.ok(flightService.updateFlight(id, flight));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable("id") Long id) {
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }
}

