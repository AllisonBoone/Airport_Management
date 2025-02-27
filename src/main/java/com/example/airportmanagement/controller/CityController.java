package com.example.airportmanagement.controller;
 
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
@RequestMapping("/cities")
public class CityController {
    private final CityService cityService;
 
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }
 

    // Get all cities.
    @GetMapping
    public ResponseEntity<List<City>> getAllCities() {
        List<City> cities = cityService.getAllCities();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    // Get city by ID.
    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(@PathVariable Long id) {
        Optional<City> city = cityService.getCityById(id);
        return city.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Create a new city.
    @PostMapping
    public ResponseEntity<City> addCity(@RequestBody City city) {
        try {
            City newCity = cityService.addCity(city);
            return new ResponseEntity<>(newCity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Update an existing city.
    @PutMapping("/{id}")
    public ResponseEntity<City> updateCity(@PathVariable Long id, @RequestBody City city) {
        
        try {
            City updatedCity = cityService.updateCity(id, city);
            return new ResponseEntity<>(updatedCity, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete an existing city.
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        return cityService.deleteCity(id).map(city -> new ResponseEntity<Void>(HttpStatus.NO_CONTENT)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
}