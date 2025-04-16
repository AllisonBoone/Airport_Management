# Airport_Management

This is the backend API for the Airport Management System built with Spring Boot, MySQL, and Docker. It provides a RESTful interface for managing cities, airports, aircraft, passengers, and flights.

---

## Features

- CRUD operations for:
  - Cities
  - Airports
  - Aircraft
  - Passengers
  - Flights
- Relational endpoints (e.g., `getAirportsByCityId`, `getAirportsUsedByPassenger`)
- Dockerized with `docker-compose.yml`
- In-memory H2 database for testing
- Comprehensive service layer unit tests
- Ready for AWS deployment

---

## Technologies Used

- Java 17
- Spring Boot 3.2.1
- Spring Data JPA (Hibernate)
- MySQL (via Docker)
- JUnit 5 & Mockito
- Maven
- Docker + Docker Compose

---

## 🔧 Local Setup Instructions

- mvn clean install
- docker-compose up --build
- mvn test
- docker build -t airport-management-backend .
- docker run -p 8081:8080 airport-management-backend
- docker-compose up

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/airport-management-backend.git
cd airport-management-backend
```
