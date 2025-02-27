package com.example.airportmanagement.model;

// Added imports.
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

// Created Entity class for passengers in database.
@Entity
@Table(name = "passengers")
public class Passenger {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @ManyToMany
    @JoinTable(
        name = "passenger_aircraft",
        joinColumns = @JoinColumn(name = "passenger_id"),
        inverseJoinColumns = @JoinColumn(name = "aircraft_id")
    )
    private Set<Aircraft> aircraft = new HashSet<>();

    // Created constructor method.
    public Passenger() {
    }

    public Passenger(String firstName, String lastName, String phoneNumber, City city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.city = city;
    }

    // Getter and Setter methods.
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public City getCity() { return city; }
    public void setCity(City city) { this.city = city; }

    public Set<Aircraft> getAircraft() { return aircraft; }
    public void setAircraft(Set<Aircraft> aircraft) { this.aircraft = aircraft; }
}
