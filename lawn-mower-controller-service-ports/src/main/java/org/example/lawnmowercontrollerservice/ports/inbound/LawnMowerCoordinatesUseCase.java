package org.example.lawnmowercontrollerservice.ports.inbound;

import reactor.core.publisher.Mono;

public interface LawnMowerCoordinatesUseCase {
    Mono<String> updateLawnMowerCoordinates();
}
