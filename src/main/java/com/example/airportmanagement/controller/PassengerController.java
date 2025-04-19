package com.example.airportmanagement.controller;

import com.example.airportmanagement.dto.PassengerDto;
import com.example.airportmanagement.service.PassengerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/passengers")
public class PassengerController {
    private final PassengerService svc;
    public PassengerController(PassengerService svc) { this.svc = svc; }

    @GetMapping
    public List<PassengerDto> list() {
        return svc.getAllPassengers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PassengerDto> get(@PathVariable Long id) {
        return svc.getPassengerById(id)
                  .map(ResponseEntity::ok)
                  .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PassengerDto> create(@RequestBody PassengerDto dto) {
        PassengerDto created = svc.addPassenger(dto);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PassengerDto> update(
            @PathVariable Long id,
            @RequestBody PassengerDto dto
    ) {
        PassengerDto updated = svc.updatePassenger(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        svc.deletePassenger(id);
        return ResponseEntity.noContent().build();
    }
}