package com.example.airportmanagement.controller;

import com.example.airportmanagement.dto.AirportDto;
import com.example.airportmanagement.service.AirportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
public class AirportController {
    private final AirportService svc;
    public AirportController(AirportService svc) {
        this.svc = svc;
    }

    @GetMapping
    public List<AirportDto> list() {
        return svc.getAllAirports();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirportDto> get(@PathVariable Long id) {
        var opt = svc.getAirportById(id);
        return opt.isPresent()
             ? ResponseEntity.ok(opt.get())
             : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<AirportDto> create(@RequestBody AirportDto d) {
        var created = svc.addAirport(d);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public AirportDto update(@PathVariable Long id, @RequestBody AirportDto d) {
        return svc.updateAirport(id, d);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        svc.deleteAirport(id);
        return ResponseEntity.noContent().build();
    }
}