package com.example.airportmanagement.integration;

import com.example.airportmanagement.model.City;
import com.example.airportmanagement.repository.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CityIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CityRepository cityRepository;

    private City city;

    @BeforeEach
    void setUp() {
        city = new City();
        city.setName("Integration City");
        city.setPopulation(100000);
        city.setProvince("Integration Province");
        city.setCountry("Integration Country");
        cityRepository.save(city);
    }

    @Test
    void testGetCity() throws Exception {
        mockMvc.perform(get("/api/cities/" + city.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Integration City"));
    }
}
