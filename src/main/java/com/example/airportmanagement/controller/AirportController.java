package com.example.airportmanagement.controller;
 
// Added imports.
import com.example.airportmanagement.model.Airport;
import com.example.airportmanagement.service.AirportService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
 
// Created controller for airport api. 
@RestController
@RequestMapping("/airports")
public class AirportController {
    private final AirportService airportService;
 
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }
 
    @GetMapping
    public List<Airport> getAllAirports() {
        return airportService.getAllAirports();
    }
}