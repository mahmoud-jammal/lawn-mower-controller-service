package org.example.lawnmowercontrollerservice.ports.inbound;

import org.example.lawnmowercontrollerservice.ports.vo.requests.UpdateCoordinatesRequestVO;
import org.example.lawnmowercontrollerservice.ports.vo.responses.UpdateCoordinatesResponseVO;
import reactor.core.publisher.Mono;

public interface LawnMowerCoordinatesUseCase {
    Mono<UpdateCoordinatesResponseVO> updateLawnMowerCoordinates(UpdateCoordinatesRequestVO updateCoordinatesRequestVO);
}
