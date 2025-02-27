package com.example.airportmanagement.repository;

// Added imports.
import com.example.airportmanagement.model.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// Created repository for doing database operations on aircraft.
public interface AircraftRepository extends JpaRepository<Aircraft, Long> {
    Optional<Aircraft> findByTypeAndAirlineName(String type, String airlineName);
}
