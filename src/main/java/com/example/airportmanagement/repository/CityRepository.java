package com.example.airportmanagement.repository;

// Added imports.
import com.example.airportmanagement.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
// Created repository for doing database operations on city.
public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findByName(String name);
    List<City> findByCountry(String country);
}