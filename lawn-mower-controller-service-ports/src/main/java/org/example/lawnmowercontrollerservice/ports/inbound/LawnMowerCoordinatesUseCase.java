package org.example.lawnmowercontrollerservice.ports.inbound;

import org.example.lawnmowercontrollerservice.ports.vo.inputs.UpdateMowerInputVO;
import org.example.lawnmowercontrollerservice.ports.vo.outputs.UpdateMowerOutputVO;
import reactor.core.publisher.Mono;

public interface LawnMowerCoordinatesUseCase {
    Mono<UpdateMowerOutputVO> updateLawnMowerCoordinates(UpdateMowerInputVO updateMowerInputVO);
}
