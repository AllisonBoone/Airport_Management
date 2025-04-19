package com.example.airportmanagement.dto;

public class CityDto {
    private Long    id;
    private String  name;
    private Integer population;
    private String  province;
    private String  country;

    public CityDto() {}

    public CityDto(Long id, String name, Integer population, String province, String country) {
        this.id         = id;
        this.name       = name;
        this.population = population;
        this.province   = province;
        this.country    = country;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getPopulation() { return population; }
    public void setPopulation(Integer population) { this.population = population; }

    public String getProvince() { return province; }
    public void setProvince(String province) { this.province = province; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}