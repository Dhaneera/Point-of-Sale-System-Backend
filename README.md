# POS-Point-of-Sale-System(Backend)
## Introduction
Welcome to the Spring Boot Point of Sale (POS) System repository. This application is designed to handle sales transactions, inventory management, and customer data for small to medium-sized businesses. The backend is built using Spring Boot, making it robust, scalable, and easy to maintain.

## Features
- **Sales Management**: Handle transactions, generate receipts, and manage daily sales.
- **Inventory Management**: Track product stock, add new products, and update existing product details.
- **Customer Management**: Maintain customer information and purchase history.
- **User Authentication**: Secure login and role-based access control.
- **Reporting**: Generate sales and inventory reports.

## Technologies Used
- **Backend**: Spring Boot
- **Database**: H2 (in-memory) for development, configurable to use MySQL or PostgreSQL for production
- **Build Tool**: Maven
- **Testing**: JUnit, Mockito

## Getting Started

### Prerequisites
- Java 8 or higher
- Maven
- Git

### Installation

1. **Clone the repository:**
    ```sh
    git clone https://github.com/yourusername/pos-system.git
    cd pos-system
    ```

2. **Build the project:**
    ```sh
    mvn clean install
    ```

3. **Run the application:**
    ```sh
    mvn spring-boot:run
    ```

### Configuration

The application uses an in-memory H2 database by default. You can configure the database settings in the `src/main/resources/application.properties` file to use a different database.
To run the front-end of this application please use this link (https://github.com/SDW-SWD/Point-of-Sale-System-Frontend)

```properties
# Default H2 Database configuration
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true

# Example MySQL configuration
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/<your database  name>     make sure to create database by going to your terminal and run codes and create database database name in your terminal
    username: root
    password: *******
  jpa:
    hibernate:
      ddl-auto: update
management:
  endpoints:
    exposure:
      include: "*"
  info:
    env:
      enabled: true


