# KimFlights

KimFlights is a Java-based web application built with Spring Boot. The application uses Spring MVC to provide RESTful web services and web application functionality.

## About

KimFlights is a Spring Boot application designed to provide flight-related services through a lightweight and modern Java web architecture. The project leverages Spring Boot's auto-configuration capabilities and follows Maven-based project management for dependency handling and build automation.

---

# Project Information

| Property         | Value             |
| ---------------- | ----------------- |
| Application Name | kimflights        |
| Group ID         | com.kimgroup      |
| Artifact ID      | kimflights        |
| Version          | 0.0.1-SNAPSHOT    |
| Java Version     | 25                |
| Build Tool       | Maven             |
| Framework        | Spring Boot 4.0.6 |

---

# Requirements

Before building or running the application, ensure the following software is installed:

## Required Software

### Java Development Kit (JDK)

* JDK 25
* Verify installation:

```bash
java --version
```

Expected output should indicate Java 25.

### Apache Maven

* Maven 3.9.x or newer

Verify installation:

```bash
mvn --version
```

### Operating Systems

The application can be built and executed on:

* Windows 10/11
* Linux
* macOS

---

# Dependencies

## Main Dependencies

### Spring Boot Web MVC

```xml
org.springframework.boot:spring-boot-starter-webmvc
```

Provides:

* Spring MVC
* Embedded Servlet Container
* REST Controller support
* JSON serialization/deserialization

### Lombok

```xml
org.projectlombok:lombok
```

Provides:

* Automatic generation of getters/setters
* Constructors
* Builders
* Reduced boilerplate code

> Lombok is used only during compilation and is excluded from the final executable JAR.

### Spring Boot DevTools

```xml
org.springframework.boot:spring-boot-devtools
```

Provides:

* Automatic restart during development
* Faster development cycles

---

## Test Dependencies

### Spring Boot MVC Test

```xml
org.springframework.boot:spring-boot-starter-webmvc-test
```

Provides:

* Mock MVC testing
* Unit and integration testing support

---

# Project Structure

```text
kimflights/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   │
│   └── test/
│       ├── java/
│       └── resources/
│
├── pom.xml
└── README.md
```

---

# Compile the Project

To compile the source code:

```bash
mvn clean compile
```

This command:

1. Cleans previous build artifacts
2. Downloads required dependencies
3. Compiles application source code

---

# Run the Application (Development Mode)

Run directly using Maven:

```bash
mvn spring-boot:run
```

The application will start using the embedded server configured by Spring Boot.

By default:

```text
http://localhost:8080
```

---

# Package the Application

To create an executable JAR file:

```bash
mvn clean package
```

After a successful build, Maven generates:

```text
target/kimflights-0.0.1-SNAPSHOT.jar
```

---

# Run the Packaged JAR

Execute the generated JAR file:

```bash
java -jar target/kimflights-0.0.1-SNAPSHOT.jar
```

The application will start and listen on the configured server port.

---

# Build Lifecycle Commands

## Clean

```bash
mvn clean
```

Removes generated build files.

## Compile

```bash
mvn compile
```

Compiles source code.

## Test

```bash
mvn test
```

Runs all unit and integration tests.

## Package

```bash
mvn package
```

Creates executable JAR.

## Install

```bash
mvn install
```

Installs artifact into the local Maven repository.

---

# Lombok Configuration

This project uses Lombok annotation processing.

If using an IDE:

## IntelliJ IDEA

1. Install Lombok plugin
2. Enable annotation processing:

   * Settings → Build, Execution, Deployment → Compiler → Annotation Processors
   * Enable "Annotation Processing"

## Eclipse

1. Install Lombok
2. Restart Eclipse

---

# Future Enhancements

Potential future additions include:

* Flight search integration
* Flight booking APIs
* Database persistence using Spring Data JPA
* User authentication and authorization
* Flight history and tracking
* API documentation with OpenAPI/Swagger

---

# Author

Kim Group

---

# License

This project is currently distributed without a specified license.
Please update this section if a license is chosen in the future.
