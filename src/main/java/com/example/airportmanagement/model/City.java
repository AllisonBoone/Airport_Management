package com.example.airportmanagement.model;
 
// Added imports.
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonInclude;
 
// Created Entity class for city in database.
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long id;
    private String name;

    @Column(name = "province")
    private String province;

    private int population;

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