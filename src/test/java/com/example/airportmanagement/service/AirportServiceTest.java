package com.example.airportmanagement.service;

import com.example.airportmanagement.dto.AirportDto;
import com.example.airportmanagement.model.Airport;
import com.example.airportmanagement.model.City;
import com.example.airportmanagement.repository.AirportRepository;
import com.example.airportmanagement.repository.CityRepository;
import jakarta.persistence.EntityNotFoundException;
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
    private AirportRepository airportRepo;

    @Mock
    private CityRepository cityRepo;

    @InjectMocks
    private AirportService airportService;

    private Airport airport;
    private City city;

    @BeforeEach
    void setUp() {
        city = new City();
        city.setId(10L);
        city.setName("TestCity");

        airport = new Airport();
        airport.setId(1L);
        airport.setName("Test Airport");
        airport.setCode("TST");
        airport.setCity(city);
    }

    @Test
    void getAllAirports_ShouldReturnDtoList() {
        when(airportRepo.findAll()).thenReturn(List.of(airport));

        List<AirportDto> dtos = airportService.getAllAirports();

        assertEquals(1, dtos.size());
        AirportDto dto = dtos.get(0);
        assertEquals(1L, dto.getId());
        assertEquals("Test Airport", dto.getName());
        assertEquals("TST", dto.getCode());
        assertEquals("TestCity", dto.getCity());
    }

    @Test
    void getAirportById_ShouldReturnDtoIfExists() {
        when(airportRepo.findById(1L)).thenReturn(Optional.of(airport));

        Optional<AirportDto> opt = airportService.getAirportById(1L);

        assertTrue(opt.isPresent());
        AirportDto dto = opt.get();
        assertEquals("Test Airport", dto.getName());
    }

    @Test
    void addAirport_ShouldLookupCityAndReturnDto() {
        when(cityRepo.findByName("TestCity")).thenReturn(Optional.of(city));
        when(airportRepo.save(any(Airport.class))).thenAnswer(invocation -> {
            Airport a = invocation.getArgument(0);
            a.setId(2L);
            return a;
        });

        AirportDto created = airportService.addAirport(
            new AirportDto(null, "New Apt", "NEW", "TestCity")
        );

        assertNotNull(created.getId());
        assertEquals("New Apt", created.getName());
        assertEquals("TestCity", created.getCity());
    }

    @Test
    void addAirport_UnknownCityShouldThrow() {
        when(cityRepo.findByName("NoSuchCity")).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () ->
            airportService.addAirport(new AirportDto(null, "X", "X", "NoSuchCity"))
        );
    }

    @Test
    void deleteAirport_ShouldThrowWhenNotExists() {
        when(airportRepo.existsById(99L)).thenReturn(false);
        assertThrows(EntityNotFoundException.class, () ->
            airportService.deleteAirport(99L)
        );
    }
}

