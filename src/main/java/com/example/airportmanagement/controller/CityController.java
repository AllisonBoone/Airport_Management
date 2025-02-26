package com.example.airportmanagement.controller;
 
// Added imports.
import com.example.airportmanagement.model.City;
import com.example.airportmanagement.service.CityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
 
// Created controller for city api.
@RestController
@RequestMapping("/cities")
public class CityController {
    private final CityService cityService;
 
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }
 
    // GET
    @GetMapping
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }
 
    // POST
    @PostMapping
    public ResponseEntity<City> addCity(@RequestBody City city) {
        City newCity = cityService.addCity(city);
        return new ResponseEntity<>(newCity, HttpStatus.CREATED);
    }
    
}