package com.example.airportmanagement.service;

import com.example.airportmanagement.dto.AirportDto;
import com.example.airportmanagement.model.Airport;
import com.example.airportmanagement.model.City;
import com.example.airportmanagement.repository.AirportRepository;
import com.example.airportmanagement.repository.CityRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AirportService {
    private final AirportRepository repo;
    private final CityRepository    cityRepo;

    public AirportService(AirportRepository repo, CityRepository cityRepo) {
        this.repo     = repo;
        this.cityRepo = cityRepo;
    }

    public List<AirportDto> getAllAirports() {
        return repo.findAll().stream()
            .map(a -> new AirportDto(
                a.getId(),
                a.getName(),
                a.getCode(),
                a.getCity().getName()
            ))
            .collect(Collectors.toList());
    }

    public Optional<AirportDto> getAirportById(Long id) {
        return repo.findById(id)
            .map(a -> new AirportDto(
                a.getId(),
                a.getName(),
                a.getCode(),
                a.getCity().getName()
            ));
    }

    public AirportDto addAirport(AirportDto d) {
        City c = cityRepo.findByName(d.getCity())
            .orElseThrow(() -> new EntityNotFoundException("Unknown city: " + d.getCity()));

        Airport a = new Airport();
        a.setName(d.getName());
        a.setCode(d.getCode());
        a.setCity(c);

        Airport saved = repo.save(a);
        return new AirportDto(
            saved.getId(),
            saved.getName(),
            saved.getCode(),
            saved.getCity().getName()
        );
    }

    public AirportDto updateAirport(Long id, AirportDto d) {
        City c = cityRepo.findByName(d.getCity())
            .orElseThrow(() -> new EntityNotFoundException("Unknown city: " + d.getCity()));

        Airport a = repo.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Airport not found: " + id));

        a.setName(d.getName());
        a.setCode(d.getCode());
        a.setCity(c);

        Airport saved = repo.save(a);
        return new AirportDto(
            saved.getId(),
            saved.getName(),
            saved.getCode(),
            saved.getCity().getName()
        );
    }

    public void deleteAirport(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Airport not found: " + id);
        }
        repo.deleteById(id);
    }
}