# Divisors Mapper

Divisors Mapper is a Java application for mapping number divisors to some specified words.

## Build & Run

Use maven command to build the project

```bash
mvn clean package
```
and command below to run

```bash
./mvnw spring-boot:run
```

## Technologies
Project is created with:
* Java 11
* Spring Boot 2.6

## Usage

Locally application is running on
```
http://localhost:8081
```

List of specified endpoints is visible in swagger documentation
```
GET /api/swagger-ui/index.html
```
Mapping list of numbers with specified category is done with request
```
GET /api/divisors/{category}/
```
and body
```json
{
    "numbers": [1, 2, 4]
}
```
where [1, 2, 4] is an example list of numbers.

List of available categories is visible with endpoint

```
GET /api/categories/
```

## Version

0.0.1-SNAPSHOT
