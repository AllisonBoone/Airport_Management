package com.example.airportmanagement.service;

// Imports.`
import com.example.airportmanagement.model.Flight;
import com.example.airportmanagement.model.Aircraft;
import com.example.airportmanagement.model.Airport;
import com.example.airportmanagement.repository.FlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlightServiceTest {

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightService flightService;

    private Flight flight;
    private Aircraft aircraft;
    private Airport departure;
    private Airport arrival;

    @BeforeEach
    void setUp() {
        aircraft = new Aircraft();
        aircraft.setId(1L);

        departure = new Airport();
        departure.setId(1L);
        departure.setName("Departure Airport");

        arrival = new Airport();
        arrival.setId(2L);
        arrival.setName("Arrival Airport");

        flight = new Flight();
        flight.setId(1L);
        flight.setAircraft(aircraft);
        flight.setDepartureAirport(departure);
        flight.setArrivalAirport(arrival);
        flight.setDepartureTime(LocalDateTime.now());
        flight.setArrivalTime(LocalDateTime.now().plusHours(2));
    }

    // Test to return flight list.
    @Test
    void getAllFlights_ShouldReturnFlightList() {
        when(flightRepository.findAll()).thenReturn(List.of(flight));

        List<Flight> result = flightService.getAllFlights();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    // Test for flight by id.
    @Test
    void getFlightById_ShouldReturnFlightIfExists() {
        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));

        Optional<Flight> result = flightService.getFlightById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    // Test for adding a flight.
    @Test
    void addFlight_ShouldSaveAndReturnFlight() {
        when(flightRepository.save(any(Flight.class))).thenReturn(flight);

        Flight saved = flightService.addFlight(flight);

        assertNotNull(saved);
        assertEquals(flight.getId(), saved.getId());
    }

    // Test for updating a flight.
    @Test
    void updateFlight_ShouldModifyAndReturnFlight() {
        Flight updated = new Flight();
        updated.setAircraft(aircraft);
        updated.setDepartureAirport(departure);
        updated.setArrivalAirport(arrival);
        updated.setDepartureTime(LocalDateTime.now().plusHours(1));
        updated.setArrivalTime(LocalDateTime.now().plusHours(3));

        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));
        when(flightRepository.save(any(Flight.class))).thenReturn(updated);

        Flight result = flightService.updateFlight(1L, updated);

        assertNotNull(result);
        assertEquals(updated.getArrivalTime(), result.getArrivalTime());
    }

    // Test for trying to delete non-existing flight.
    @Test
    void deleteFlight_ShouldThrowIfNotExists() {
        when(flightRepository.existsById(99L)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> flightService.deleteFlight(99L));
    }
}

