package com.example.airportmanagement.service;

import com.example.airportmanagement.dto.*;
import com.example.airportmanagement.model.*;
import com.example.airportmanagement.repository.CityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {
    private final CityRepository repo;

    public CityService(CityRepository repo) {
        this.repo = repo;
    }

    public List<CityDto> getAllCities() {
        return repo.findAll()
                   .stream()
                   .map(DtoMapper::toDto)
                   .collect(Collectors.toList());
    }

    public CityDto getCityById(Long id) {
        return repo.findById(id)
                   .map(DtoMapper::toDto)
                   .orElseThrow(() -> new EntityNotFoundException("City not found: " + id));
    }

    public CityDto addCity(CityDto dto) {
        City entity = DtoMapper.toEntity(dto);
            City saved  = repo.save(entity);
                return DtoMapper.toDto(saved);
    }

    public CityDto updateCity(Long id, CityDto dto) {
        City updated = repo.findById(id)
            .map(e -> {
                e.setName(dto.getName());
                e.setPopulation(dto.getPopulation());
                e.setProvince(dto.getProvince());
                e.setCountry(dto.getCountry());
                return repo.save(e);
            })
            .orElseThrow(() -> new EntityNotFoundException("City not found: " + id));
        return DtoMapper.toDto(updated);
    }

    public void deleteCity(Long id) {
        if (!repo.existsById(id))
            throw new EntityNotFoundException("City not found: " + id);
        repo.deleteById(id);
    }

    public List<AirportDto> getAirportsByCity(Long id) {
        City city = repo.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("City not found: " + id));
        return city.getAirports()
                   .stream()
                   .map(DtoMapper::toDto)
                   .collect(Collectors.toList());
    }
}