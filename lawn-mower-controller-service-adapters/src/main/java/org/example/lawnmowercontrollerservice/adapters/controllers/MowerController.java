package org.example.lawnmowercontrollerservice.adapters.controllers;

import lombok.RequiredArgsConstructor;
import org.example.lawnmowercontrollerservice.ports.inbound.LawnMowerCoordinatesUseCase;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/lawn-mower-controller-service/mowers")
@RequiredArgsConstructor
public class MowerController {

    private final LawnMowerCoordinatesUseCase lawnMowerCoordinatesUseCase;

    @PutMapping(path = "/coordinates")
    public Mono<String> updateMowersCoordinates() {
        return lawnMowerCoordinatesUseCase.updateLawnMowerCoordinates();
    }
}
