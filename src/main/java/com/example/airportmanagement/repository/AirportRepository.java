package com.example.airportmanagement.repository;
 
// Added imports.
import com.example.airportmanagement.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
// Created repository for airport database operations.
public interface AirportRepository extends JpaRepository<Airport, Long> {
    Optional<Airport> findByName(String name);
    List<Airport> findByCityId(Long cityId);
}