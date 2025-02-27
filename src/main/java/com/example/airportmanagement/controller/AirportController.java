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
@RequestMapping("/airports")
public class AirportController {
    private final AirportService airportService;
 
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }
 
    // Get all airports.
    @GetMapping
    public ResponseEntity<List<Airport>> getAllAirports() {
        return new ResponseEntity<>(airportService.getAllAirports(), HttpStatus.OK);
    }

    // Get airport by ID.
    @GetMapping("/{id}")
    public ResponseEntity<Airport> getAirportById(@PathVariable Long id) {
        Optional<Airport> airport = airportService.getAirportById(id);
        return airport.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                      .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Add new airport.
    @PostMapping
    public ResponseEntity<Airport> addAirport(@RequestBody Airport airport) {
        return new ResponseEntity<>(airportService.addAirport(airport), HttpStatus.CREATED);
    }

    // Update airport.
    @PutMapping("/{id}")
    public ResponseEntity<Airport> updateAirport(@PathVariable Long id, @RequestBody Airport updatedAirport) {
        try {
            return new ResponseEntity<>(airportService.updateAirport(id, updatedAirport), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete existing airport.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirport(@PathVariable Long id) {
        return airportService.deleteAirport(id).map(airport -> new ResponseEntity<Void>(HttpStatus.NO_CONTENT)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}