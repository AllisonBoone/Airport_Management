package com.example.airportmanagement.model;

// Added imports.
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.HashSet;
import java.util.Set;
// Created Entity class for aircraft in database.
@Entity
@Table(name = "aircraft")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String airlineName;

    @Column(nullable = false)
    private int numberOfPassengers;

    @ManyToMany(mappedBy = "aircraft")
    @JsonBackReference
    private Set<Passenger> passengers = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "aircraft_airport",
        joinColumns = @JoinColumn(name = "aircraft_id"),
        inverseJoinColumns = @JoinColumn(name = "airport_id")
    )
    @JsonManagedReference
    private Set<Airport> airports = new HashSet<>();

    // Created constructor method.
    public Aircraft() {}

    public Aircraft(String type, String airlineName, int numberOfPassengers) {
        this.type = type;
        this.airlineName = airlineName;
        this.numberOfPassengers = numberOfPassengers;
    }

    // Created getters and setters methods.
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getAirlineName() { return airlineName; }
    public void setAirlineName(String airlineName) { this.airlineName = airlineName; }

    public int getNumberOfPassengers() { return numberOfPassengers; }
    public void setNumberOfPassengers(int numberOfPassengers) { this.numberOfPassengers = numberOfPassengers; }

    public Set<Passenger> getPassengers() { return passengers; }
    public void setPassengers(Set<Passenger> passengers) { this.passengers = passengers; }
}
