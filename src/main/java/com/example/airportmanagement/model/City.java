package com.example.airportmanagement.model;
 
// Added imports.
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonInclude;
 
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

    @Column(name = "province", nullable = false, length = 100)
    private String province;
    
    @Column(name = "population", nullable = false)
    private Integer population;

    @Column(name = "country", nullable = false, length = 100)
    private String country;

    // Created constructor method.
    public City() {}

    public City(String name, String province, Integer population) {
        this.name = name;
        this.province = province;
        this.population = population;
        this.country = country;
    }

    // Created getter and setter methods.
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
 
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
 
    public String getProvince() { return province; }
    public void setProvince(String province) { this.province = province; }
 
    public int getPopulation() { return population; }
    public void setPopulation(Integer population) { this.population = population; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
   
}