package com.example.airportmanagement.model;
 
// Added imports.
import jakarta.persistence.*;
 
// Created entity class for airport in database.
@Entity
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String code;
 
    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    // Created constructor method.
    public Airport() {}

    public Airport(String name, String code, City city) {
        this.name = name;
        this.code = code;
        this.city = city;
    }

    // Created getter and setter methods.
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public City getCity() { return city; }
    public void setCity(City city) { this.city = city; }

}
 