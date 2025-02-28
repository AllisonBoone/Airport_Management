package com.example.airportmanagement;
 
// Added imports.
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

 
// Created main class to run SpringBoot.
@SpringBootApplication
@EnableWebMvc
@EnableJpaRepositories("com.example.airportmanagement.repository")
@EntityScan("com.example.airportmanagement.model")
public class AirportManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(AirportManagementApplication.class, args);
    }
}