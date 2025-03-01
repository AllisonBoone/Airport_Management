package com.example.airportmanagement.repository;

// Added imports.
import com.example.airportmanagement.model.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;
// Created repository for doing database operations on aircraft.
public interface AircraftRepository extends JpaRepository<Aircraft, Long> {
    List<Aircraft> findByAirlineName(String airlineName);

    @Query("SELECT a FROM Aircraft a WHERE a.type = :type AND a.airlineName = :airlineName")
    Optional<Aircraft> findByTypeAndAirlineName(@Param("type") String type, @Param("airlineName") String airlineName);

    @Query("SELECT a FROM Aircraft a JOIN a.airports ap WHERE ap.id = :airportId")
    List<Aircraft> findByAirportId(@Param("airportId") Long airportId);
}