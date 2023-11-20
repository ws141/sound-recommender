# Sound recommender

### Built with

* Maven
* Spring Boot in Kotlin
* Postgresql with Testcontainers (docker required)
* Api generated from OpenApi specification

### Run application

`./mvnw spring-boot:run`

### Tests

I added a few repository tests and an end-to-end test to match the flow showcased by the postman collection
Run with `./mvnw test`

### Own endpoints
In addition to the endpoints required by the assignment I chose to add the following endpoints:
* Update playlist at `PATCH /playlist/{id}`
* Get playlist at `GET /playlist/{id}`

These endpoints felt like an organic next addition.

### Further development
* Add update and get endpoints for the sound resource
* Add test for the service layer with mocking
