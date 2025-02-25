package com.example.airportmanagement.repository;

// Added imports.
import com.example.airportmanagement.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

// Created repository for doing database operations on city.
public interface CityRepository extends JpaRepository<City, Long> {
}