package org.example.lawnmowercontrollerservice.adapters.controllers;

import lombok.RequiredArgsConstructor;
import org.example.lawnmowercontrollerservice.ports.inbound.LawnMowerCoordinatesUseCase;
import org.example.lawnmowercontrollerservice.ports.vo.requests.UpdateCoordinatesRequestVO;
import org.example.lawnmowercontrollerservice.ports.vo.responses.UpdateCoordinatesResponseVO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/lawn-mower-controller-service/mowers")
@RequiredArgsConstructor
public class MowerController {

    private final LawnMowerCoordinatesUseCase lawnMowerCoordinatesUseCase;

    @PutMapping(path = "/coordinates", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<UpdateCoordinatesResponseVO> updateMowersCoordinates(@RequestBody UpdateCoordinatesRequestVO updateCoordinatesRequestVO) {
        return lawnMowerCoordinatesUseCase.updateLawnMowerCoordinates(updateCoordinatesRequestVO);
    }
}
