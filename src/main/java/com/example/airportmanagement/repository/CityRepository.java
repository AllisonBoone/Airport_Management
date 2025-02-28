package com.example.airportmanagement.repository;

// Added imports.
import com.example.airportmanagement.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;
// Created repository for doing database operations on city.
public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findByName(String name);
    List<City> findByProvince(String province);

    @Query("SELECT c FROM City c LEFT JOIN FETCH c.airports WHERE c.id = :id")
    Optional<City> findByIdWithRelations(@Param("id") Long id);
}