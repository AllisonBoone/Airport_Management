package com.example.airportmanagement.service;
 
// Added imports.
import com.example.airportmanagement.model.Airport;
import com.example.airportmanagement.repository.AirportRepository;
import org.springframework.stereotype.Service;
import java.util.List;
 
// Created service class for airport logic.
@Service
public class AirportService {
    private final AirportRepository airportRepository;
 
    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }
 
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }
}