package org.example.lawnmowercontrollerservice.integrationtests;

import org.example.lawnmowercontrollerservice.app.LawnMowerControllerServiceApp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(classes = LawnMowerControllerServiceApp.class,
                webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LawnMowerCoordinatesIT {

    private static final String UPDATE_MOWERS_COORDINATES_PATH = "/lawn-mower-controller-service/mowers/coordinates";
    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void updateCoordinatesTest() {
        webTestClient.put()
                     .uri("http://localhost:" + port + UPDATE_MOWERS_COORDINATES_PATH)
                     .exchange()
                     .expectBody(String.class);
    }
}
