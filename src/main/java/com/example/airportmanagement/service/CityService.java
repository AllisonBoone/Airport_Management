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

    // Grt city by ID.
    public Optional<City> getCityById(Long id) {
        return cityRepository.findById(id);
    }

    // Add city.
    @Transactional
    public City addCity(City city) {
        return cityRepository.save(city);
    }


    // Update city.
    @Transactional
    public City updateCity(Long id, City updatedCity) {
        return cityRepository.findById(id).map(city -> {
            city.setName(updatedCity.getName());
            city.setProvince(updatedCity.getProvince());
            return cityRepository.save(city);
        }).orElseThrow(() -> new IllegalArgumentException("City not found"));
    }


    // Delete city.
    @Transactional
    public void deleteCity(Long id) {
        if (!cityRepository.existsById(id)) {
            throw new IllegalArgumentException("City not found");
        }
        cityRepository.deleteById(id);
    }
}