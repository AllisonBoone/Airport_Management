package com.example.airportmanagement.service;
 
// Added imports.
import com.example.airportmanagement.model.City;
import com.example.airportmanagement.repository.CityRepository;
import org.springframework.stereotype.Service;
import java.util.List;
 
// Created service class for city logic.
@Service
public class CityService {
    private final CityRepository cityRepository;
 
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }
 
    // Getter method.
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }
 
    // Method to add a city.
    public City addCity(City city) {
        return cityRepository.save(city);
    }
}