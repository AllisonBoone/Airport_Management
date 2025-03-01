package com.example.airportmanagement.service;

import com.example.airportmanagement.model.City;
import com.example.airportmanagement.repository.CityRepository;
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
    private CityRepository cityRepository;

    @InjectMocks
    private CityService cityService;

    private City city;

    @BeforeEach
    void setUp() {
        city = new City();
        city.setId(1L);
        city.setName("Test City");
        city.setPopulation(500000);
        city.setProvince("Test Province");
        city.setCountry("Test Country");
    }

    @Test
    void getAllCities_ShouldReturnCityList() {
        when(cityRepository.findAll()).thenReturn(List.of(city));

        List<City> cities = cityService.getAllCities();

        assertFalse(cities.isEmpty());
        assertEquals(1, cities.size());
        assertEquals("Test City", cities.get(0).getName());
    }

    @Test
    void getCityById_ShouldReturnCityIfExists() {
        when(cityRepository.findById(1L)).thenReturn(Optional.of(city));

        Optional<City> foundCity = cityService.getCityById(1L);

        assertTrue(foundCity.isPresent());
        assertEquals("Test City", foundCity.get().getName());
    }

    @Test
    void addCity_ShouldSaveAndReturnCity() {
        when(cityRepository.save(any(City.class))).thenReturn(city);

        City savedCity = cityService.addCity(city);

        assertNotNull(savedCity);
        assertEquals("Test City", savedCity.getName());
    }

    @Test
    void deleteCity_ShouldThrowExceptionIfNotFound() {
        when(cityRepository.existsById(99L)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> cityService.deleteCity(99L));
    }
}
