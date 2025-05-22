# lawn-mower-controller-service

A reactive Java 17 Spring WebFlux application that simulates automatic lawn mowers navigating within a bounded
rectangular grid.

It uses:

- **Java 17**
- **Spring Boot + WebFlux** for non-blocking request handling
- **Custom Exceptions** for clean error reporting
- **Reactive Testing** with `WebTestClient`

## APIs

```http
PUT /lawn-mower-controller-service/mowers/coordinates
```

Update mowers coordinates based on a sequence of input lines taking in count that the mower should not get out of the
garden borders.

#### Request

The request body should have the following format:
* First line should be the upper right angle of the garden, this helps us to know the maximum coordinates of the garden,
  as we suppose the bottom left angle of the garden is (0,0).
* After we should have two lines for each Mower with the following format:
  * First line will be the current coordinates of the mower (to be updated)
  * Second line will be the instructions of directions which could have the following values:
    * F: Move forward
    * L: Rotate to the left without moving.
    * R: Rotate the right without moving.

##### Example

```json
{
  "inputLines": [
    "5 5",
    "1 2 N",
    "LFLFLFLFF",
    "3 3 E",
    "FFRFFRFRRF"
  ]
}
```

#### Response

The response would be a sequence of new coordinates, each line of the sequence would represent the new coordinates per
each mower.

##### Example
```json
{
  "newCoordinates": [
    "1 3 N",
    "5 1 E"
  ]
}
```

### OpenAPI

To learn more about provided APIs a swagger is available when the application is running at

http://localhost:8080/webjars/swagger-ui/index.html

## Error handling

Custom exceptions return clear, structured error messages:

- `InputLinesHasIncorrectLengthException`: If Input lines provided by request body, doesn't have %2 number of lines
  without counting the upper right lines (first line).
- `UpperRightAxisNotFoundException`: If first line doesn't correspond to the format of axis X Y value.
- `WrongCoordinatesValueException`: If first line of mower sequence doesn't correspond to X Y A-Z.
- `WrongDirectionCharacterException`: If the direction specified in coordinates line doesn't belong to (N, W, E, S).
- `WrongInstructionValueException`: If second line of mower sequence doesn't correspond to combination of the following
  letters R,L,F.

## Tests

### To run tests

```bash
./mvnw clean verify
```

#### Integration tests

- Happy flows.
- Unhappy flows.

## Run locally

```bash
./mvnw spring-boot:run
```

The app will be available at:

```
http://localhost:8080/lawn-mower-controller-service/mowers/coordinates
```

## Architecture

The application is written using Hexagonal architecture with the following components:

### Adapters

External components that has nothing to do with the core of the applications (e.g API controllers)

### Ports

Common classes/objects that are needed to be used between adapters and core layers.

### Core

Domain/core of the application.

### App

Entry point and Spring config.