<h1 align="center">CRUD ONE TO MANY</h1>

## Description

Demo Spring Boot application that implements a REST API to perform CRUD operations on business model entities 
for a platform where users can create and manage posts. In this model, the entities `USER` and `Post` 
have a one-to-many relationship, i.e., one user can have multiple publications.

## Technical stack

* Java 21
* Spring Boot
* Spring Web MVC
* Spring Data JPA
* PostgreSQL

## Install

**Clone the repository:**

```bash
git clone https://github.com/Vasyabylba/crud-one-to-many.git
cd crud-one-to-many
```

### Manually

#### Database connection

A driver for connecting to PostgreSQL database has been added to the application. To run the application, 
make sure that you have PostgreSQL Server running.

##### Create database

Create the `demo_crud` database.

The connection parameters to the PostgreSQL database are already configured in the file:
`src/main/resources/application.yml`

```yaml
spring:
    datasource:
        driver-class-name: org.postgresql.Driver
        password: postgres
        username: postgres
        url: jdbc:postgresql://localhost:5432/demo_crud
```

If necessary, change the connection parameters in the configuration file
`src/main/resources/application.yml`:

```yaml
spring:
    datasource:
        driver-class-name: org.postgresql.Driver
        username: {user_name}
        password: {user_password}
        url: jdbc:postgresql://localhost:5432/{database_name}
```

##### Create tables

Use the script `src/main/resources/database.sql` to create tables.

#### RUN

**Build and run the project:**
<ul>
  <li>Windows</li>

```bash
mvnw.cmd clean install
mvnw.cmd spring-boot:run
```
  <li>Unix system</li>

```bash
./mvnw clean install
./mvnw spring-boot:run
```
</ul>

## Usage

Postman Collection: https://elements.getpostman.com/redirect?entityId=29103818-4a0fdb25-bdd3-4262-ab5a-110b2d0cfa6a&entityType=collection