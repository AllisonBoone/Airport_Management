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

    // Get city by name.
    public Optional<City> getCityByName(String name) {
        return cityRepository.findByName(name);
    }

    // Get cities by country.
    public List<City> getCitiesByCountry(String country) {
        return cityRepository.findByCountry(country);
    }
 
    // Add new city with error handling.
    @Transactional
    public City addCity(City city) {

        if (city.getName() == null || city.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("City name can not be empty.");
        }

        if (city.getCountry() == null || city.getCountry().trim().isEmpty()) {
            throw new IllegalArgumentException("Country name can not be empty.");
        }

        if (cityRepository.findByName(city.getName()).isPresent()) {
            throw new IllegalArgumentException("This city already exists.");
        }

        return cityRepository.save(city);
    }

    // Update existing city with error handling.
    @Transactional
    public City updateCity(Long id, City updatedCity) {

        if (updatedCity.getName() == null || updatedCity.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Updated city name can not be empty.");
        }

        if (updatedCity.getCountry() == null || updatedCity.getCountry().trim().isEmpty()) {
            throw new IllegalArgumentException("Updated country name can not be empty.");
        }

        return cityRepository.findById(id).map(city -> {
            city.setName(updatedCity.getName());
            city.setCountry(updatedCity.getCountry());
            return cityRepository.save(city);
        }).orElseThrow(() -> new IllegalArgumentException("City with the ID of " + id + "was not found."));
    }

    // Delete existing city.
    @Transactional
    public Optional<City> deleteCity(Long id) {
        Optional<City> cityToDelete = cityRepository.findById(id);
        cityToDelete.ifPresent(cityRepository::delete);
        return cityToDelete;
    }
}