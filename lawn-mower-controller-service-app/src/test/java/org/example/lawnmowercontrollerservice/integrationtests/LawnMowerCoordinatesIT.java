package org.example.lawnmowercontrollerservice.integrationtests;

import org.example.lawnmowercontrollerservice.adapters.vo.requests.UpdateCoordinatesRequestVO;
import org.example.lawnmowercontrollerservice.adapters.vo.responses.UpdateCoordinatesResponseVO;
import org.example.lawnmowercontrollerservice.app.LawnMowerControllerServiceApp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;

@SpringBootTest(classes = LawnMowerControllerServiceApp.class,
                webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LawnMowerCoordinatesIT {

    private static final String UPDATE_MOWERS_COORDINATES_PATH = "/lawn-mower-controller-service/mowers/coordinates";
    private static final UpdateCoordinatesResponseVO EXPECTED_COORDINATES = new UpdateCoordinatesResponseVO(List.of("1 3 N", "5 1 E"));

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void updateCoordinatesTest() {
        Mono<UpdateCoordinatesRequestVO> requestBody =
                Mono.just(new UpdateCoordinatesRequestVO(List.of("5 5", "1 2 N", "LFLFLFLFF", " ", "3 3 E", " ", "FFRFFRFRRF")));
        webTestClient.put()
                     .uri(UPDATE_MOWERS_COORDINATES_PATH)
                     .contentType(MediaType.APPLICATION_JSON)
                     .body(requestBody, UpdateCoordinatesRequestVO.class)
                     .exchange()
                     .expectHeader()
                     .contentType(MediaType.APPLICATION_JSON)
                     .expectStatus()
                     .isOk()
                     .expectBody(UpdateCoordinatesResponseVO.class)
                     .isEqualTo(EXPECTED_COORDINATES);
    }
}
