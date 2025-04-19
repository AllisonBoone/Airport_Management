package com.example.airportmanagement.service;

import com.example.airportmanagement.dto.CityDto;
import com.example.airportmanagement.model.City;
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
class CityServiceTest {

    @Mock
    private CityRepository cityRepo;

    @InjectMocks
    private CityService cityService;

    private City city;

    @BeforeEach
    void setUp() {
        city = new City();
        city.setId(1L);
        city.setName("Test City");
        city.setPopulation(500_000);
        city.setProvince("Test Province");
        city.setCountry("Test Country");
    }

    @Test
    void getAllCities_ShouldReturnDtoList() {
        when(cityRepo.findAll()).thenReturn(List.of(city));

        List<CityDto> dtos = cityService.getAllCities();

        assertEquals(1, dtos.size());
        CityDto dto = dtos.get(0);
        assertEquals(1L, dto.getId());
        assertEquals("Test City", dto.getName());
        assertEquals(500_000, dto.getPopulation());
    }

    @Test
    void getCityById_ShouldReturnDtoIfExists() {
        when(cityRepo.findById(1L)).thenReturn(Optional.of(city));

        CityDto dto = cityService.getCityById(1L);

        assertNotNull(dto);
        assertEquals("Test City", dto.getName());
    }

    @Test
    void getCityById_NotFoundShouldThrow() {
        when(cityRepo.findById(99L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () ->
            cityService.getCityById(99L)
        );
    }

    @Test
    void addCity_ShouldSaveAndReturnDto() {
        when(cityRepo.save(any(City.class))).thenReturn(city);

        CityDto dto = cityService.addCity(
            new CityDto(null, "Test City", 500_000, "Test Province", "Test Country")
        );

        assertEquals("Test City", dto.getName());
        assertEquals("Test Country", dto.getCountry());
    }

    @Test
    void deleteCity_ShouldThrowWhenNotExists() {
        when(cityRepo.existsById(99L)).thenReturn(false);
        assertThrows(EntityNotFoundException.class, () ->
            cityService.deleteCity(99L)
        );
    }
}
