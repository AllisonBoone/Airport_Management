package com.example.airportmanagement.service;

import com.example.airportmanagement.dto.FlightDto;
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

    private Flight flightEntity;
    private Aircraft aircraft;
    private Airport departure;
    private Airport arrival;

    @BeforeEach
    void setUp() {
        aircraft = new Aircraft();
        aircraft.setId(1L);

        departure = new Airport();
        departure.setId(1L);

        arrival = new Airport();
        arrival.setId(2L);

        flightEntity = new Flight();
        flightEntity.setId(1L);
        flightEntity.setAircraft(aircraft);
        flightEntity.setDepartureAirport(departure);
        flightEntity.setArrivalAirport(arrival);
        flightEntity.setDepartureTime(LocalDateTime.now());
        flightEntity.setArrivalTime(LocalDateTime.now().plusHours(2));
    }

    @Test
    void getAllFlights_ShouldReturnFlightDtoList() {
        when(flightRepository.findAll()).thenReturn(List.of(flightEntity));

        List<FlightDto> result = flightService.getAllFlights();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
    }

    @Test
    void getFlightById_ShouldReturnFlightDtoIfExists() {
        when(flightRepository.findById(1L)).thenReturn(Optional.of(flightEntity));

        Optional<FlightDto> result = flightService.getFlightById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void addFlight_ShouldSaveAndReturnFlightDto() {
        FlightDto inputDto = new FlightDto(null, "FN123", 1L, 1L, 2L, LocalDateTime.now(), LocalDateTime.now().plusHours(2));
        when(flightRepository.save(any(Flight.class))).thenReturn(flightEntity);

        FlightDto result = flightService.addFlight(inputDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void updateFlight_ShouldSaveAndReturnFlightDto() {
        FlightDto updateDto = new FlightDto(null, "FN456", 1L, 1L, 2L, LocalDateTime.now(), LocalDateTime.now().plusHours(3));
        when(flightRepository.findById(1L)).thenReturn(Optional.of(flightEntity));
        when(flightRepository.save(any(Flight.class))).thenReturn(flightEntity);

        FlightDto result = flightService.updateFlight(1L, updateDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void deleteFlight_ShouldInvokeRepository() {
        doNothing().when(flightRepository).deleteById(1L);

        flightService.deleteFlight(1L);

        verify(flightRepository, times(1)).deleteById(1L);
    }
}
