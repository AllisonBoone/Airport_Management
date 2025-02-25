package com.example.airportmanagement.model;
 
// Added imports.
import jakarta.persistence.*;
import lombok.*;
 
// Created Entity class for city in database.
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    private String name;

    @Column(name = "province")
    private String province;

    private int population;
   
}