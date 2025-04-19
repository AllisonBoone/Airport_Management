package com.example.airportmanagement.dto;

public class AirportDto {
    private Long id;
    private String name;
    private String code;
    private String city;

    public AirportDto() {}

    // Constructor
    public AirportDto(Long id, String name, String code, String city) {
        this.id   = id;
        this.name = name;
        this.code = code;
        this.city = city;
    }

    // Getter and setter.
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getCity() { return city; }      
    public void setCity(String city) { this.city = city; } 
}