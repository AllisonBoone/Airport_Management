package com.example.airportmanagement.service;

import com.example.airportmanagement.model.Passenger;
import com.example.airportmanagement.repository.PassengerRepository;
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
class PassengerServiceTest {

    @Mock
    private PassengerRepository passengerRepository;

    @InjectMocks
    private PassengerService passengerService;

    private Passenger passenger;

    @BeforeEach
    void setUp() {
        passenger = new Passenger();
        passenger.setId(1L);
        passenger.setFirstName("John");
        passenger.setLastName("Doe");
        passenger.setPhoneNumber("1234567890");
    }

    @Test
    void getAllPassengers_ShouldReturnPassengerList() {
        when(passengerRepository.findAll()).thenReturn(List.of(passenger));

        List<Passenger> passengers = passengerService.getAllPassengers();

        assertFalse(passengers.isEmpty());
        assertEquals(1, passengers.size());
    }

    @Test
    void getPassengerById_ShouldReturnPassengerIfExists() {
        when(passengerRepository.findById(1L)).thenReturn(Optional.of(passenger));

        Optional<Passenger> foundPassenger = passengerService.getPassengerById(1L);

        assertTrue(foundPassenger.isPresent());
    }
}
