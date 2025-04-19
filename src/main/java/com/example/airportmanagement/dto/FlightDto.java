package com.example.airportmanagement.dto;

import java.time.LocalDateTime;

public class FlightDto {
    private Long id;
    private String flightNumber;
    private Long aircraftId;
    private Long departureAirportId;
    private Long arrivalAirportId;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    public FlightDto() {}
    public FlightDto(Long id, String flightNumber, Long aircraftId, Long departureAirportId, Long arrivalAirportId,
                     LocalDateTime departureTime, LocalDateTime arrivalTime) {
        this.id = id; this.flightNumber = flightNumber; this.aircraftId = aircraftId;
        this.departureAirportId = departureAirportId; this.arrivalAirportId = arrivalAirportId;
        this.departureTime = departureTime; this.arrivalTime = arrivalTime;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }
    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Long getAircraftId() {
        return aircraftId;
    }
    public void setAircraftId(Long aircraftId) {
        this.aircraftId = aircraftId;
    }

    public Long getDepartureAirportId() {
        return departureAirportId;
    }
    public void setDepartureAirportId(Long departureAirportId) {
        this.departureAirportId = departureAirportId;
    }

    public Long getArrivalAirportId() {
        return arrivalAirportId;
    }
    public void setArrivalAirportId(Long arrivalAirportId) {
        this.arrivalAirportId = arrivalAirportId;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }
    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }
    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}

