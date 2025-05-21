package org.example.lawnmowercontrollerservice.core.usecases;

import org.example.lawnmowercontrollerservice.ports.inbound.LawnMowerCoordinatesUseCase;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class LawnMowerCoordinatesUseCaseImp implements LawnMowerCoordinatesUseCase {
    @Override
    public Mono<String> updateLawnMowerCoordinates() {
        return Mono.just("Coordinates are updated !");
    }
}
