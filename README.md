# Sound recommender

### Tools

* Java 17
* Kotlin 1.8.22
* Maven
* Spring Boot in Kotlin
* Formatted with ktlint 1.0.0
* Postgres 16 with Testcontainers (docker required)
* API generated from OpenApi specification

### Run application
#### Maven
`./mvnw spring-boot:run`

#### IntelliJ IDEA
Make sure to compile with maven first by running `./mvnw clean compile` and mark `target/generated-sources` directory as "Sources Root" by right clicking the folder
and choosing `'Mark Directory as' -> 'Soures Root'`

Then run `main` function in `SoundRecommenderApplication`

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
