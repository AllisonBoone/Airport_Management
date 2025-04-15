package com.example.airportmanagement.controller;
 
// Added imports.
import com.example.airportmanagement.model.Airport;
import com.example.airportmanagement.service.AirportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
 
// Created controller for airport api. 
@RestController
@RequestMapping("/api/airports")
public class AirportController {
    private final AirportService airportService;
 
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }
 
    // Get all airports.
    @GetMapping
    public ResponseEntity<List<Airport>> getAllAirports() {
        return ResponseEntity.ok(airportService.getAllAirports());
    }

    // Get airport by ID.
    @GetMapping("/{id}")
    public ResponseEntity<Airport> getAirportById(@PathVariable("id") Long id) {
        Optional<Airport> airport = airportService.getAirportById(id);
        return airport.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Add new airport.
    @PostMapping
    public ResponseEntity<Airport> addAirport(@RequestBody Airport airport) {
        return new ResponseEntity<>(airportService.addAirport(airport), HttpStatus.CREATED);
    }

    // Update airport.
    @PutMapping("/{id}")
    public ResponseEntity<Airport> updateAirport(@PathVariable("id") Long id, @RequestBody Airport airport) {
        return ResponseEntity.ok(airportService.updateAirport(id, airport));
    }

    // Delete existing airport.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirport(@PathVariable("id") Long id) {
        airportService.deleteAirport(id);
        return ResponseEntity.noContent().build();
    }
}