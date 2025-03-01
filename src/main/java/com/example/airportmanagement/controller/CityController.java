package com.example.airportmanagement.controller;
 
import com.example.airportmanagement.model.Airport;
// Added imports.
import com.example.airportmanagement.model.City;
import com.example.airportmanagement.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
 
// Created controller for city api.
@RestController
@RequestMapping("/api/cities")
public class CityController {
    private final CityService cityService;
 
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }
 

    // Get all cities.
    @GetMapping
    public ResponseEntity<List<City>> getAllCities() {
        return ResponseEntity.ok(cityService.getAllCities());
    }

    // Get city by ID.
    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(@PathVariable Long id) {
        Optional<City> city = cityService.getCityById(id);
        return city.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Add new city.
    @PostMapping
    public ResponseEntity<City> addCity(@RequestBody City city) {
        return new ResponseEntity<>(cityService.addCity(city), HttpStatus.CREATED);
    }

    // Update an existing city.
    @PutMapping("/{id}")
    public ResponseEntity<City> updateCity(@PathVariable Long id, @RequestBody City city) {
        return ResponseEntity.ok(cityService.updateCity(id, city));
    }

    // Delete an existing city.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        cityService.deleteCity(id);
        return ResponseEntity.noContent().build();
    }

    // Get all airports in city.
    @GetMapping("/{id}/airports")
    public ResponseEntity<List<Airport>> getAirportsByCity(@PathVariable Long id) {
        return cityService.getCityById(id)
                .map(city -> ResponseEntity.ok(city.getAirports()))
                .orElse(ResponseEntity.notFound().build());
    }
}