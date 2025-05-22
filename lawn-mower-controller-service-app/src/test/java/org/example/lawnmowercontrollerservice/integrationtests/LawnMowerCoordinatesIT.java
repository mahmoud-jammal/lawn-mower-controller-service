package org.example.lawnmowercontrollerservice.integrationtests;

import org.example.lawnmowercontrollerservice.adapters.advice.ErrorResponseDTO;
import org.example.lawnmowercontrollerservice.adapters.vo.requests.UpdateCoordinatesRequestVO;
import org.example.lawnmowercontrollerservice.adapters.vo.responses.UpdateCoordinatesResponseVO;
import org.example.lawnmowercontrollerservice.app.LawnMowerControllerServiceApp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Stream;

@SpringBootTest(classes = LawnMowerControllerServiceApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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

    @Test
    void updateCoordinatesWithoutUpperRightValueTest() {
        Mono<UpdateCoordinatesRequestVO> requestBody =
                Mono.just(new UpdateCoordinatesRequestVO(List.of("1 2 N", "1 2 N", "LFLFLFLFF", " ", "3 3 E", " ", "FFRFFRFRRF")));
        webTestClient.put()
                     .uri(UPDATE_MOWERS_COORDINATES_PATH)
                     .contentType(MediaType.APPLICATION_JSON)
                     .body(requestBody, UpdateCoordinatesRequestVO.class)
                     .exchange()
                     .expectHeader()
                     .contentType(MediaType.APPLICATION_JSON)
                     .expectStatus()
                     .isNotFound()
                     .expectBody(ErrorResponseDTO.class)
                     .isEqualTo(new ErrorResponseDTO("E4040", "Upper right axis line is not found or incorrect"));
    }

    @ParameterizedTest(name = "{0}")
    @MethodSource("badRequestsCases")
    void updateCoordinatesWithWrongInputLengthTest(String testName, List<String> inputLines, String errorCode, String errorMessage) {
        Mono<UpdateCoordinatesRequestVO> requestBody = Mono.just(new UpdateCoordinatesRequestVO(inputLines));
        webTestClient.put()
                     .uri(UPDATE_MOWERS_COORDINATES_PATH)
                     .contentType(MediaType.APPLICATION_JSON)
                     .body(requestBody, UpdateCoordinatesRequestVO.class)
                     .exchange()
                     .expectHeader()
                     .contentType(MediaType.APPLICATION_JSON)
                     .expectStatus()
                     .isBadRequest()
                     .expectBody(ErrorResponseDTO.class)
                     .isEqualTo(new ErrorResponseDTO(errorCode, errorMessage));
    }

    public static Stream<Arguments> badRequestsCases() {
        return Stream.of(
                Arguments.of("updateCoordinatesWithWrongInputLengthTest", List.of("5 5", "1 2 N", "LFLFLFLFF", " ", "3 3 E", " "),
                             "E4000", "Input lines length is incorrect"),
                Arguments.of("updateCoordinatesWithWrongInstructionValueTest", List.of("5 5", "1 2 N", "AFLFLFLFF", " ", "3 3 E", " ", "FFRFFRFRRF"),
                             "E4001", "Instruction characters should only be 'L', 'R' or 'F'"),
                Arguments.of("updateCoordinatesWithWrongCoordinatesValueTest", List.of("5 5", "1 2 N", "LFLFLFLFF", " ", "B 3 E", " ", "FFRFFRFRRF"),
                             "E4002", "Coordinates characters should be of format digit digit letter"),
                Arguments.of("updateCoordinatesWithWrongDirectionValueTest", List.of("5 5", "1 2 H", "LFLFLFLFF", " ", "3 3 E", " ", "FFRFFRFRRF"),
                             "E4003", "Direction value should be one of the following letters: 'N', 'E', 'W','S'")
                        );
    }
}
