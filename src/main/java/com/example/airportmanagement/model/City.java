package com.example.airportmanagement.model;
 
// Added imports.
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.HashSet;
import java.util.Set;
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

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Airport> airports = new HashSet<>();

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Passenger> passengers = new HashSet<>();

    // Created constructor method.
    public City() {}

    public City(String name, String province, Integer population, String country) {
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

    public Set<Airport> getAirports() { return airports; }
    public void setAirports(Set<Airport> airports) { this.airports = airports; }

    public Set<Passenger> getPassengers() { return passengers; }
    public void setPassengers(Set<Passenger> passengers) { this.passengers = passengers; }
   
}