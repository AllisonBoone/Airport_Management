package com.example.airportmanagement.service;

import com.example.airportmanagement.model.Airport;
import com.example.airportmanagement.repository.AirportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AirportServiceTest {

    @Mock
    private AirportRepository airportRepository;

    @InjectMocks
    private AirportService airportService;

    private Airport airport;

    @BeforeEach
    void setUp() {
        airport = new Airport();
        airport.setId(1L);
        airport.setName("Test Airport");
        airport.setCode("TST");
    }

    @Test
    void getAllAirports_ShouldReturnAirportList() {
        when(airportRepository.findAll()).thenReturn(List.of(airport));

        List<Airport> airports = airportService.getAllAirports();

        assertFalse(airports.isEmpty());
        assertEquals(1, airports.size());
    }

    @Test
    void getAirportById_ShouldReturnAirportIfExists() {
        when(airportRepository.findById(1L)).thenReturn(Optional.of(airport));

        Optional<Airport> foundAirport = airportService.getAirportById(1L);

        assertTrue(foundAirport.isPresent());
    }

    @Test
    void addAirport_ShouldSaveAndReturnAirport() {
        when(airportRepository.save(any(Airport.class))).thenReturn(airport);

        Airport savedAirport = airportService.addAirport(airport);

        assertNotNull(savedAirport);
        assertEquals("Test Airport", savedAirport.getName());
    }

    @Test
    void deleteAirport_ShouldThrowExceptionIfNotFound() {
        when(airportRepository.existsById(99L)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> airportService.deleteAirport(99L));
    }
}
