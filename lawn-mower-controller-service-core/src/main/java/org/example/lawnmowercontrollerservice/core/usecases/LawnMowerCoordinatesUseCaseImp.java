package org.example.lawnmowercontrollerservice.core.usecases;

import lombok.RequiredArgsConstructor;
import org.example.lawnmowercontrollerservice.core.dtos.AxisDTO;
import org.example.lawnmowercontrollerservice.core.mappers.MowerModelMapper;
import org.example.lawnmowercontrollerservice.core.models.MowerModel;
import org.example.lawnmowercontrollerservice.ports.inbound.LawnMowerCoordinatesUseCase;
import org.example.lawnmowercontrollerservice.ports.vo.inputs.UpdateMowerInputVO;
import org.example.lawnmowercontrollerservice.ports.vo.outputs.UpdateMowerOutputVO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LawnMowerCoordinatesUseCaseImp implements LawnMowerCoordinatesUseCase {

    private final MowerModelMapper mowerModelMapper;

    @Override
    public Mono<UpdateMowerOutputVO> updateLawnMowerCoordinates(UpdateMowerInputVO updateMowerInputVO) {
        List<String> updatedCoordinates = getUpdatedMowersCoordinates(mowerModelMapper.mapToMowerModelList(updateMowerInputVO.mowerInputVos())
                , mowerModelMapper.mapToAxisDTO(updateMowerInputVO.upperRightAxisInputVO()));
        return Mono.just(mapToUpdateMowerOutputVO(updatedCoordinates));
    }

    private UpdateMowerOutputVO mapToUpdateMowerOutputVO(List<String> updatedCoordinates) {
        return new UpdateMowerOutputVO(updatedCoordinates);
    }

    private List<String> getUpdatedMowersCoordinates(List<MowerModel> mowerModels, AxisDTO upperRightAxis) {
        return mowerModels.stream().map(mowerModel -> mowerModel.executeInstructions(upperRightAxis)).toList();
    }
}
