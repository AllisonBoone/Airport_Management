package com.example.airportmanagement.controller;

import com.example.airportmanagement.dto.AircraftDto;
import com.example.airportmanagement.service.AircraftService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aircraft")
public class AircraftController {
    private final AircraftService svc;
    public AircraftController(AircraftService svc) { this.svc = svc; }

    @GetMapping
    public List<AircraftDto> list() {
        return svc.getAllAircraft();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AircraftDto> get(@PathVariable Long id) {
        return svc.getAircraftById(id)
                  .map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AircraftDto> create(@RequestBody AircraftDto d) {
        AircraftDto created = svc.addAircraft(d);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public AircraftDto update(@PathVariable Long id, @RequestBody AircraftDto d) {
        return svc.updateAircraft(id, d);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        svc.deleteAircraft(id);
        return ResponseEntity.noContent().build();
    }
}
