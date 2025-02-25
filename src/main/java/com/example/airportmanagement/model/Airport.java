package com.example.airportmanagement.model;
 
// Added imports.
import jakarta.persistence.*;
import lombok.*;
 
// Created entity class for airport in database.
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;
    private String name;
    private String code;
 
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;
}
 