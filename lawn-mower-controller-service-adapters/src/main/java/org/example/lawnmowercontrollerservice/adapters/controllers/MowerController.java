package org.example.lawnmowercontrollerservice.adapters.controllers;

import lombok.RequiredArgsConstructor;
import org.example.lawnmowercontrollerservice.adapters.mappers.UpdateMowerVOMapper;
import org.example.lawnmowercontrollerservice.adapters.vo.requests.UpdateCoordinatesRequestVO;
import org.example.lawnmowercontrollerservice.adapters.vo.responses.UpdateCoordinatesResponseVO;
import org.example.lawnmowercontrollerservice.ports.inbound.LawnMowerCoordinatesUseCase;
import org.example.lawnmowercontrollerservice.ports.vo.outputs.UpdateMowerOutputVO;
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
    private final UpdateMowerVOMapper updateMowerVOMapper;

    @PutMapping(path = "/coordinates", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<UpdateCoordinatesResponseVO> updateMowersCoordinates(@RequestBody UpdateCoordinatesRequestVO updateCoordinatesRequestVO) {
        Mono<UpdateMowerOutputVO> updatedMowerOutputMono =
                lawnMowerCoordinatesUseCase.updateLawnMowerCoordinates(updateMowerVOMapper.mapToUpdateMowerInputVO(updateCoordinatesRequestVO));
        return updateMowerVOMapper.mapToUpdateCoordinatesResponseVO(updatedMowerOutputMono);
    }
}
