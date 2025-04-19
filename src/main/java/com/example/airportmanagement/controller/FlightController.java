package com.example.airportmanagement.controller;

import com.example.airportmanagement.dto.FlightDto;
import com.example.airportmanagement.service.FlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {
    private final FlightService svc;
    public FlightController(FlightService svc) { this.svc = svc; }

    @GetMapping
    public List<FlightDto> list() {
        return svc.getAllFlights();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightDto> get(@PathVariable Long id) {
        return svc.getFlightById(id)
                  .map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FlightDto> create(@RequestBody FlightDto d) {
        FlightDto created = svc.addFlight(d);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public FlightDto update(@PathVariable Long id, @RequestBody FlightDto d) {
        return svc.updateFlight(id, d);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        svc.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }
}
