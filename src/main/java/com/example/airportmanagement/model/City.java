package com.example.airportmanagement.model;
 
// Added imports.
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Objects;
 
// Created Entity class for city in database.
@Entity
@Table(name = "city")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    @Column(name = "province", nullable = false, unique = true, length = 100)
    private String province;
    
    @Column(name = "population", nullable = false)
    private int population;

    // Created constructor method.
    public City() {}

    public City(String name, String province, int population) {
        this.name = name;
        this.province = province;
        this.population = population;
    }

    // Created getter and setter methods.
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
 
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
 
    public String getProvince() { return province; }
    public void setProvince(String province) { this.province = province; }
 
    public int getPopulation() { return population; }
    public void setPopulation(int population) { this.population = population; }
   
}