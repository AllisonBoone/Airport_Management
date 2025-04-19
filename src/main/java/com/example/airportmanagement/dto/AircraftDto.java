package com.example.airportmanagement.dto;

public class AircraftDto {
    private Long id;
    private String model;
    private String manufacturer;
    private Integer capacity;

    public AircraftDto() {}

    public AircraftDto(Long id, String model, String manufacturer, Integer capacity) {
        this.id = id;
        this.model = model;
        this.manufacturer = manufacturer;
        this.capacity = capacity;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getManufacturer() { return manufacturer; }
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }

    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }
}

