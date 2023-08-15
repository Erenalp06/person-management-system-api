# Person Management System API

![GitHub repo size](https://img.shields.io/github/repo-size/Erenalp06/person-management-system-api)
![GitHub stars](https://img.shields.io/github/stars/Erenalp06/person-management-system-api?style=social)
![GitHub forks](https://img.shields.io/github/forks/Erenalp06/person-management-system-api?style=social)
![Twitter Follow](https://img.shields.io/twitter/follow/Erenalp11191435?style=social)

The Person Management System API is a powerful Spring Boot application that allows seamless management of individuals through a comprehensive set of features,
including listing, adding, and updating people in a database.
It even intelligently retrieves missing individuals from an external source when necessary, ensuring accurate and efficient data management.

* [Requirements](#requirements)
* [Used Technologies](#used-technologies)
* [Features](#features)
* [Getting Started](#getting-started)
* [License](#license)
* [Explore Rest API](#explore-rest-api)

## Requirements ##
- Java 8 or higher
- PostgreSQL database
- Docker (optional, for containerization of the project)

## Used Technologies ## 
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
  - Logging with slf4j

## Features ##
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
  - Using logger for both functionality and feature tracking

## Getting Started ##

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

Appendix A.

All commands should be run from project root (where docker-compose.yml locates)

* If you have to want to see running containers.
Checklist docker containers
```bash
docker container list -a
```
or
```bash
docker-compose ps
```

The app will start running at <http:localhost:9643>

**5. Swagger Documentation**
* Explore the interactive Swagger documentation by simply visiting the following URL in your web browser:
```bash
http://localhost:9643/swagger-ui.html 
```

## Contributing ##

Contributions are welcome! For significant changes, please open an issue to discuss first.

  1. Fork this repository.
  2. Create a new branch (git checkout -b feature/AmazingFeature).
  3. Make changes and commit them (git commit -m 'Add some AmazingFeature').
  4. Push to your branch (git push origin feature/AmazingFeature).
  5. Open a Pull Request.

## License ##

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.<br>
Developer: Erenalp TEKŞEN <br>
Project Link: https://github.com/Erenalp06/person-management-system-api

## Explore Rest API's ##

The app defines following CRUP APIs.

### Users

| Method | URL | Description | Sample Valid Request Body |
| ------ | --- | ----------- | ------------------------- |
| GET    | /api/v1/users | Get all users | |
| GET    | /api/v1/users/{username} | Get user by username | |
| POST   | /api/v1/users | Create new user | [JSON](#) |
| PUT    | /api/v1/users/{userId} | Update user | |
| DELETE | /api/v1/users/{username} | Delete user by username | |

### People

| Method | URL | Description | Sample Valid Request Body |
| ------ | --- | ----------- | ------------------------- |
| GET    | /api/v1/people | Get all people | |
| GET    | /api/v1/people/{id} | Get person by id| |
| POST   | /api/v1/people | Create new person | [JSON](#) |
| PUT    | /api/v1/people/{id} | Update person by id | |
| DELETE | /api/v1/people/{id} | Delete user by id | |

### Authority

| Method | URL | Description | Sample Valid Request Body |
| ------ | --- | ----------- | ------------------------- |
| GET    | /api/v1/authorities/{username} | Get a list of authorities by username | |
| POST   | /api/v1/authorities/{username}/{roleName} | Create new authority by username | [JSON](#) |
| PUT    | /api/v1/authorities/{username}/{roleName} | Update user authority by roleName | |
| DELETE | /api/v1/authorities/{username}/{roleName} | Delete user authority by roleName | |



