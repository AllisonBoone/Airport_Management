package com.example.airportmanagement.service;

import com.example.airportmanagement.dto.AircraftDto;
import com.example.airportmanagement.model.Aircraft;
import com.example.airportmanagement.repository.AircraftRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AircraftServiceTest {

    @Mock
    private AircraftRepository aircraftRepository;

    @InjectMocks
    private AircraftService aircraftService;

    private Aircraft aircraftEntity;

    @BeforeEach
    void setUp() {
        aircraftEntity = new Aircraft();
        aircraftEntity.setId(1L);
        aircraftEntity.setType("Boeing 747");
        aircraftEntity.setAirlineName("Test Airline");
        aircraftEntity.setNumberOfPassengers(300);
    }

    @Test
    void getAllAircraft_ShouldReturnAircraftDtoList() {
        when(aircraftRepository.findAll()).thenReturn(List.of(aircraftEntity));

        List<AircraftDto> result = aircraftService.getAllAircraft();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Boeing 747", result.get(0).getModel());
    }

    @Test
    void getAircraftById_ShouldReturnAircraftDtoIfExists() {
        when(aircraftRepository.findById(1L)).thenReturn(Optional.of(aircraftEntity));

        Optional<AircraftDto> result = aircraftService.getAircraftById(1L);

        assertTrue(result.isPresent());
        assertEquals("Boeing 747", result.get().getModel());
    }

    @Test
    void addAircraft_ShouldSaveAndReturnAircraftDto() {
        AircraftDto inputDto = new AircraftDto(null, "Airbus A320", "Test Airline", 180);
        Aircraft savedEntity = new Aircraft();
        savedEntity.setId(2L);
        savedEntity.setType("Airbus A320");
        savedEntity.setAirlineName("Test Airline");
        savedEntity.setNumberOfPassengers(180);

        when(aircraftRepository.save(any(Aircraft.class))).thenReturn(savedEntity);

        AircraftDto result = aircraftService.addAircraft(inputDto);

        assertNotNull(result);
        assertEquals(2L, result.getId());
        assertEquals("Airbus A320", result.getModel());
    }

    @Test
    void updateAircraft_ShouldSaveAndReturnAircraftDto() {
        AircraftDto updateDto = new AircraftDto(null, "Boeing 777", "Test Airline", 350);
        Aircraft updatedEntity = new Aircraft();
        updatedEntity.setId(1L);
        updatedEntity.setType("Boeing 777");
        updatedEntity.setAirlineName("Test Airline");
        updatedEntity.setNumberOfPassengers(350);

        when(aircraftRepository.findById(1L)).thenReturn(Optional.of(aircraftEntity));
        when(aircraftRepository.save(any(Aircraft.class))).thenReturn(updatedEntity);

        var result = aircraftService.updateAircraft(1L, updateDto);

        assertNotNull(result);
        assertEquals("Boeing 777", result.getModel());
    }

    @Test
    void deleteAircraft_ShouldInvokeRepository() {
        doNothing().when(aircraftRepository).deleteById(1L);

        aircraftService.deleteAircraft(1L);

        verify(aircraftRepository, times(1)).deleteById(1L);
    }
}