package com.example.airportmanagement.repository;
 
// Added imports.
import com.example.airportmanagement.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
 

// Created repository for airport database operations.
public interface AirportRepository extends JpaRepository<Airport, Long> {
    List<Airport> findByCityId(Long cityId);
}