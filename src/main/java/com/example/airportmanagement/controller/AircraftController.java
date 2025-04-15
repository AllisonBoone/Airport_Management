package com.example.airportmanagement.controller;

// Added imports.
import com.example.airportmanagement.model.Aircraft;
import com.example.airportmanagement.service.AircraftService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
// Created controller for aircraft api. 
@RestController
@RequestMapping("/api/aircraft")
public class AircraftController {
    private final AircraftService aircraftService;

    public AircraftController(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }

    // Get all aircraft.
    @GetMapping
    public ResponseEntity<List<Aircraft>> getAllAircraft() {
        return ResponseEntity.ok(aircraftService.getAllAircraft());
    }

    // Get aircraft by ID.
    @GetMapping("/{id}")
    public ResponseEntity<Aircraft> getAircraftById(@PathVariable("id") Long id) {
        Optional<Aircraft> aircraft = aircraftService.getAircraftById(id);
        return aircraft.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Add new aircraft.
    @PostMapping
    public ResponseEntity<Aircraft> addAircraft(@RequestBody Aircraft aircraft) {
        return new ResponseEntity<>(aircraftService.addAircraft(aircraft), HttpStatus.CREATED);
    }

    // Update aircraft.
    @PutMapping("/{id}")
    public ResponseEntity<Aircraft> updateAircraft(@PathVariable("id") Long id, @RequestBody Aircraft aircraft) {
        return ResponseEntity.ok(aircraftService.updateAircraft(id, aircraft));
    }

    // Delete existing aircraft.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAircraft(@PathVariable("id") Long id) {
        aircraftService.deleteAircraft(id);
        return ResponseEntity.noContent().build();
    }

    // Get aircraft by airport.
    @GetMapping("/{id}/airports")
    public ResponseEntity<List<String>> getAirportsUsedByAircraft(@PathVariable("id") Long id) {
        List<String> airports = aircraftService.getAirportsUsedByAircraft(id);
        return ResponseEntity.ok(airports);
    }
}
