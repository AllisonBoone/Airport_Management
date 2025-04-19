package com.example.airportmanagement.service;

import com.example.airportmanagement.dto.*;
import com.example.airportmanagement.model.*;
import com.example.airportmanagement.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class FlightService {
    private final FlightRepository flightRepo;
    private final AircraftRepository acRepo;
    private final AirportRepository apRepo;

    public FlightService(FlightRepository flightRepo, AircraftRepository acRepo, AirportRepository apRepo) {
        this.flightRepo = flightRepo;
        this.acRepo = acRepo;
        this.apRepo = apRepo;
    }

    public List<FlightDto> getAllFlights() {
        return flightRepo.findAll().stream().map(DtoMapper::toDto).collect(Collectors.toList());
    }
    public Optional<FlightDto> getFlightById(Long id) {
        return flightRepo.findById(id).map(DtoMapper::toDto);
    }
    public FlightDto addFlight(FlightDto d) {
        Flight f = DtoMapper.toEntity(d);
        var ac = acRepo.findById(d.getAircraftId()).orElseThrow(() -> new IllegalArgumentException("Invalid aircraft"));
        var dep = apRepo.findById(d.getDepartureAirportId()).orElseThrow(() -> new IllegalArgumentException("Invalid departure"));
        var arr = apRepo.findById(d.getArrivalAirportId()).orElseThrow(() -> new IllegalArgumentException("Invalid arrival"));
        f.setAircraft(ac); f.setDepartureAirport(dep); f.setArrivalAirport(arr);
        return DtoMapper.toDto(flightRepo.save(f));
    }
    public FlightDto updateFlight(Long id, FlightDto d) {
        Flight e = flightRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Flight not found: " + id));
        e.setFlightNumber(d.getFlightNumber());
        if (d.getAircraftId()!=null) e.setAircraft(acRepo.findById(d.getAircraftId()).orElseThrow(() -> new IllegalArgumentException("Invalid aircraft")));
        if (d.getDepartureAirportId()!=null) e.setDepartureAirport(apRepo.findById(d.getDepartureAirportId()).orElseThrow(() -> new IllegalArgumentException("Invalid departure")));
        if (d.getArrivalAirportId()!=null) e.setArrivalAirport(apRepo.findById(d.getArrivalAirportId()).orElseThrow(() -> new IllegalArgumentException("Invalid arrival")));
        return DtoMapper.toDto(flightRepo.save(e));
    }
    public void deleteFlight(Long id) { flightRepo.deleteById(id); }
}
