package com.example.airportmanagement.service;
 
// Added imports.
import com.example.airportmanagement.model.City;
import com.example.airportmanagement.repository.CityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
 
// Created service class for city logic.
@Service
public class CityService {
    private final CityRepository cityRepository;
 
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }
 
    // Get all cities.
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    // Get single city using city ID.
    public Optional<City> getCityById(Long id) {
        return cityRepository.findById(id);
    }
 
    // Add new city with error handling.
    @Transactional
    public City addCity(City city) {

        if (city.getName() == null || city.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("City name can not be empty.");
        }

        Optional<City> existingCity = cityRepository.findByName(city.getName());

        if (existingCity.isPresent()) {
            throw new IllegalArgumentException("This city already exists.");
        }

        return cityRepository.save(city);
    }

    // Update existing city with error handling.
    @Transactional
    public City updateCity(Long id, City updatedCity) {
        return cityRepository.findById(id).map(city -> {
            city.setName(updatedCity.getName());
            city.setCountry(updatedCity.getCountry());
            return cityRepository.save(city);
        }).orElseThrow(() -> new IllegalArgumentException("City with the ID of " + id + "not found."));
    }

    // Delete existing city.
    @Transactional
    public void deleteCity(Long id) {
        if (!cityRepository.existsById(id)) {
            throw new IllegalArgumentException("City with the ID of " + id + "not found.");
        }

        cityRepository.deleteById(id);

    }
}