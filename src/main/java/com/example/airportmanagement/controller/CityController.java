package com.example.airportmanagement.controller;

import com.example.airportmanagement.dto.CityDto;
import com.example.airportmanagement.service.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
public class CityController {
    private final CityService svc;
    public CityController(CityService svc) {
        this.svc = svc;
    }

    @GetMapping
    public List<CityDto> list() {
        return svc.getAllCities();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDto> get(@PathVariable Long id) {
        // svc.getCityById now returns null if not found
        CityDto dto = svc.getCityById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<CityDto> create(@RequestBody CityDto d) {
        CityDto created = svc.addCity(d);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public CityDto update(@PathVariable Long id, @RequestBody CityDto d) {
        return svc.updateCity(id, d);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        svc.deleteCity(id);
        return ResponseEntity.noContent().build();
    }
}