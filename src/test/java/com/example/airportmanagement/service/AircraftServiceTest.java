package com.example.airportmanagement.service;

import com.example.airportmanagement.model.Aircraft;
import com.example.airportmanagement.repository.AircraftRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AircraftServiceTest {

    @Mock
    private AircraftRepository aircraftRepository;

    @InjectMocks
    private AircraftService aircraftService;

    private Aircraft aircraft;

    @BeforeEach
    void setUp() {
        aircraft = new Aircraft();
        aircraft.setId(1L);
        aircraft.setType("Boeing 747");
        aircraft.setAirlineName("Test Airline");
    }

    @Test
    void getAllAircraft_ShouldReturnAircraftList() {
        when(aircraftRepository.findAll()).thenReturn(List.of(aircraft));

        List<Aircraft> aircraftList = aircraftService.getAllAircraft();

        assertFalse(aircraftList.isEmpty());
        assertEquals(1, aircraftList.size());
    }

    @Test
    void getAircraftById_ShouldReturnAircraftIfExists() {
        when(aircraftRepository.findById(1L)).thenReturn(Optional.of(aircraft));

        Optional<Aircraft> foundAircraft = aircraftService.getAircraftById(1L);

        assertTrue(foundAircraft.isPresent());
    }

    @Test
    void addAircraft_ShouldSaveAndReturnAircraft() {
        when(aircraftRepository.save(any(Aircraft.class))).thenReturn(aircraft);

        Aircraft savedAircraft = aircraftService.addAircraft(aircraft);

        assertNotNull(savedAircraft);
        assertEquals("Boeing 747", savedAircraft.getType());
    }

    @Test
        void deleteAircraft_ShouldThrowExceptionIfNotFound() {
            when(aircraftRepository.existsById(99L)).thenReturn(false);

            assertThrows(RuntimeException.class, () -> aircraftService.deleteAircraft(99L));
        }
}
