package com.example.airportmanagement.service;

import com.example.airportmanagement.dto.PassengerDto;
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

    private Passenger passengerEntity;

    @BeforeEach
    void setUp() {
        passengerEntity = new Passenger();
        passengerEntity.setId(1L);
        passengerEntity.setFirstName("John");
        passengerEntity.setLastName("Doe");
        passengerEntity.setPhoneNumber("9999999");
    }

    @Test
    void getAllPassengers_ShouldReturnPassengerDtoList() {
        when(passengerRepository.findAll()).thenReturn(List.of(passengerEntity));

        List<PassengerDto> result = passengerService.getAllPassengers();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstName());
    }

    @Test
    void getPassengerById_ShouldReturnPassengerDtoIfExists() {
        when(passengerRepository.findById(1L)).thenReturn(Optional.of(passengerEntity));

        Optional<PassengerDto> result = passengerService.getPassengerById(1L);

        assertTrue(result.isPresent());
        assertEquals("John", result.get().getFirstName());
    }

    @Test
    void addPassenger_ShouldSaveAndReturnPassengerDto() {
        PassengerDto inputDto = new PassengerDto(null, "Jane", "Smith", "jane@example.com", null);
        Passenger savedEntity = new Passenger();
        savedEntity.setId(2L);
        savedEntity.setFirstName("Jane");
        savedEntity.setLastName("Smith");
        savedEntity.setPhoneNumber("9999999");

        when(passengerRepository.save(any(Passenger.class))).thenReturn(savedEntity);

        PassengerDto result = passengerService.addPassenger(inputDto);

        assertNotNull(result);
        assertEquals(2L, result.getId());
        assertEquals("Jane", result.getFirstName());
    }

    @Test
    void updatePassenger_ShouldSaveAndReturnPassengerDto() {
        PassengerDto updateDto = new PassengerDto(null, "John", "Updated", "john@example.com", null);
        Passenger updatedEntity = new Passenger();
        updatedEntity.setId(1L);
        updatedEntity.setFirstName("John");
        updatedEntity.setLastName("Updated");
        updatedEntity.setPhoneNumber("9999999");

        when(passengerRepository.findById(1L)).thenReturn(Optional.of(passengerEntity));
        when(passengerRepository.save(any(Passenger.class))).thenReturn(updatedEntity);

        PassengerDto result = passengerService.updatePassenger(1L, updateDto);

        assertNotNull(result);
        assertEquals("Updated", result.getLastName());
    }

    @Test
    void deletePassenger_ShouldInvokeRepository() {
        doNothing().when(passengerRepository).deleteById(1L);

        passengerService.deletePassenger(1L);

        verify(passengerRepository, times(1)).deleteById(1L);
    }
}
