# Person Management System API

![GitHub repo size](https://img.shields.io/github/repo-size/Erenalp06/person-management-system-api)
![GitHub stars](https://img.shields.io/github/stars/Erenalp06/person-management-system-api?style=social)
![GitHub forks](https://img.shields.io/github/forks/Erenalp06/person-management-system-api?style=social)
![Twitter Follow](https://img.shields.io/twitter/follow/Erenalp11191435?style=social)

The Person Management System API is a powerful Spring Boot application that allows seamless management of individuals through a comprehensive set of features,
including listing, adding, and updating people in a database.
It even intelligently retrieves missing individuals from an external source when necessary, ensuring accurate and efficient data management.

## Used Technologies
  - Java 8+
  - Spring Boot
  - Spring Data JPA
  - PostgreSQL
  - Caching (In Memory)
  - Basic Authentication
  - Swagger for API Documentation
  - Flyway for Database Migrations
  - Docker for Containerization
  - Spring Actuator for Monitoring
  - Asynchronous Processing (Spring Async)
  - Validation (Hibernate Validator)
  - External API Integration
  - Git for Version Control

## Features
  - List, add, and update people in the database
  - Retrieve missing persons from an external API source
  - Caching mechanism improves performance
  - Secure API access via basic authentication
  - Interactive API documentation with Swagger
  - Version-controlled database migrations with Flyway
  - Easy containerization with Docker
  - Health monitoring with Actuator
  - Database operations with Data JPA
  - Ensure data integrity with validation
  - Improve performance with asynchronous processing

## Getting Started

Follow these steps to run the project on your local machine.

**1. Docker. First, you need to install docker**

* Download Docker [Here](https://docs.docker.com/docker-for-windows/install/). Hint: Enable Hyper-V feature on Windows and restart.
* Then open powershell and check:

docker version
```bash
docker -v
```

or docker compose version
```bash
docker-compose -v
```

**2. Create PostgreSQL database**
```bash
create database todo_db
```

**3. Change postgreSQL username and password as per your installation**

+ open `src/main/resources/application.properties`
+ change `spring.datasource.username` and `spring.datasource.password` as per your postgreSQL installation

**4. Spring Boot app**
* Clone the repository
```bash
git clone https://github.com/Erenalp06/person-management-system-api.git
```
* Build the maven project:
```bash
mvn clean install
```
* Running the containers:

This command will build the docker containers and start them.
```bash
docker-compose up
```

The app will start running at <http:localhost:9643>

**5. Swagger Documentation**
* Explore the interactive Swagger documentation by simply visiting the following URL in your web browser:
```bash
http://localhost:9643/swagger-ui.html 
```


