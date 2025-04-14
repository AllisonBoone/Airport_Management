package com.example.airportmanagement.repository;

// Imports.
import com.example.airportmanagement.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// Created repository for doing database operations on flight.
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByAircraftId(Long aircraftId);
    List<Flight> findByDepartureAirportId(Long airportId);
    List<Flight> findByArrivalAirportId(Long airportId);
}

