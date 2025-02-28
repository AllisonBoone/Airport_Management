package com.example.airportmanagement.repository;

// Added imports.
import com.example.airportmanagement.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;
// Created repository for doing database operations on passenger.
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
    Optional<Passenger> findByFirstNameAndLastName(String firstName, String lastName);
    List<Passenger> findByCityId(Long cityId);

    @Query("SELECT p FROM Passenger p JOIN p.aircraft a WHERE a.id = :aircraftId")
    List<Passenger> findPassengersByAircraftId(@Param("aircraftId") Long aircraftId);
}

