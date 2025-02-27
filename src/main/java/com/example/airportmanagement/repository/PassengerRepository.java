package com.example.airportmanagement.repository;

// Added imports.
import com.example.airportmanagement.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// Created repository for doing database operations on passenger.
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    Optional<Passenger> findByFirstNameAndLastName(String firstName, String lastName);
}

