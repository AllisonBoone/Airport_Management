package com.example.airportmanagement.service;

import com.example.airportmanagement.dto.PassengerDto;
import com.example.airportmanagement.dto.DtoMapper;
import com.example.airportmanagement.model.Passenger;
import com.example.airportmanagement.model.City;
import com.example.airportmanagement.repository.PassengerRepository;
import com.example.airportmanagement.repository.CityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PassengerService {
    private final PassengerRepository repo;
    private final CityRepository cityRepo;

    public PassengerService(PassengerRepository repo, CityRepository cityRepo) {
        this.repo = repo;
        this.cityRepo = cityRepo;
    }

    public List<PassengerDto> getAllPassengers() {
        return repo.findAll().stream().map(DtoMapper::toDto).collect(Collectors.toList());
    }

    public Optional<PassengerDto> getPassengerById(Long id) {
        return repo.findById(id).map(DtoMapper::toDto);
    }

    public PassengerDto addPassenger(PassengerDto dto) {
        City city = cityRepo.findById(dto.getCityId())
            .orElseThrow(() -> new IllegalArgumentException("Invalid city ID: " + dto.getCityId()));
        Passenger toSave = DtoMapper.toEntity(dto, city);
        Passenger saved = repo.save(toSave);
        return DtoMapper.toDto(saved);
    }

    public PassengerDto updatePassenger(Long id, PassengerDto dto) {
        City city = cityRepo.findById(dto.getCityId())
            .orElseThrow(() -> new IllegalArgumentException("Invalid city ID: " + dto.getCityId()));
        Passenger updated = repo.findById(id)
            .map(e -> {
                e.setFirstName(dto.getFirstName());
                e.setLastName(dto.getLastName());
                e.setPhoneNumber(dto.getPhoneNumber());
                e.setCity(city);
                return repo.save(e);
            })
            .orElseThrow(() -> new EntityNotFoundException("Passenger not found: " + id));
        return DtoMapper.toDto(updated);
    }

    public void deletePassenger(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Passenger not found: " + id);
        }
        repo.deleteById(id);
    }
}