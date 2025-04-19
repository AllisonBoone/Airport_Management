package com.example.airportmanagement.dto;

import com.example.airportmanagement.model.*;

public class DtoMapper {

    // Flight
    public static FlightDto toDto(Flight e) {
        return new FlightDto(
            e.getId(),
            e.getFlightNumber(),
            e.getAircraft().getId(),
            e.getDepartureAirport().getId(),
            e.getArrivalAirport().getId(),
            e.getDepartureTime(),
            e.getArrivalTime()
        );
    }

    public static Flight toEntity(FlightDto d) {
        Flight e = new Flight();
        e.setId(d.getId());
        e.setFlightNumber(d.getFlightNumber());
        e.setDepartureTime(d.getDepartureTime());
        e.setArrivalTime(d.getArrivalTime());
        return e;
    }

    // Passenger
    public static PassengerDto toDto(Passenger e) {
        return new PassengerDto(
            e.getId(),
            e.getFirstName(),
            e.getLastName(),
            e.getPhoneNumber(),    
            e.getCity().getId()
        );
    }

    public static Passenger toEntity(PassengerDto d, City city) {
        Passenger e = new Passenger();
        e.setFirstName(d.getFirstName());
        e.setLastName(d.getLastName());
        e.setPhoneNumber(d.getPhoneNumber()); 
        e.setCity(city);
        return e;
    }

    // Airport
    public static AirportDto toDto(Airport e) {
        return new AirportDto(
            e.getId(),
            e.getName(),
            e.getCode(),
            e.getCity().getName()
        );
    }

    public static Airport toEntity(AirportDto d, City city) {
        Airport e = new Airport();
        e.setId(d.getId());
        e.setName(d.getName());
        e.setCode(d.getCode());
        e.setCity(city);
        return e;
    }

    // City
    public static CityDto toDto(City e) {
        return new CityDto(
            e.getId(),
            e.getName(),
            e.getPopulation(),
            e.getProvince(),
            e.getCountry()
        );
    }

    public static City toEntity(CityDto d) {
        City e = new City();
        e.setId(d.getId());
        e.setName(d.getName());
        e.setPopulation(d.getPopulation());
        e.setProvince(d.getProvince());
        e.setCountry(d.getCountry());
        return e;
    }

    // Aircraft
    public static AircraftDto toDto(Aircraft e) {
        return new AircraftDto(
            e.getId(),
            e.getType(),
            e.getAirlineName(),
            e.getNumberOfPassengers()
        );
    }

    public static Aircraft toEntity(AircraftDto d) {
        Aircraft e = new Aircraft();
        e.setId(d.getId());
        e.setType(d.getModel());
        e.setAirlineName(d.getManufacturer());
        e.setNumberOfPassengers(d.getCapacity());
        return e;
    }
}
