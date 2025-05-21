package org.example.lawnmowercontrollerservice.core.mappers;

import lombok.RequiredArgsConstructor;
import org.example.lawnmowercontrollerservice.core.dtos.AxisDTO;
import org.example.lawnmowercontrollerservice.core.dtos.CoordinatesDTO;
import org.example.lawnmowercontrollerservice.core.enums.Direction;
import org.example.lawnmowercontrollerservice.core.models.MowerModel;
import org.example.lawnmowercontrollerservice.ports.vo.inputs.UpdateMowerInputVO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MowerModelMapper {

    public List<MowerModel> mapToMowerModelList(List<UpdateMowerInputVO.MowerInputVo> mowerInputVos) {
        return mowerInputVos.stream()
                            .map(mowerInputVo -> MowerModel.builder()
                                                           .instructions(mowerInputVo.instructions())
                                                           .coordinatesDTO(mapToCoordinatesDTO(mowerInputVo.coordinatesInputVO()))
                                                           .build())
                            .toList();
    }

    private CoordinatesDTO mapToCoordinatesDTO(UpdateMowerInputVO.MowerInputVo.CoordinatesInputVO coordinatesInputVO) {
        return CoordinatesDTO.builder()
                             .axisDTO(mapToAxisDTO(coordinatesInputVO.axisInputVO()))
                             .direction(Direction.fromString(coordinatesInputVO.direction()))
                             .build();
    }

    public AxisDTO mapToAxisDTO(UpdateMowerInputVO.AxisInputVO axisInputVO) {
        return AxisDTO.builder()
                      .x(axisInputVO.x())
                      .y(axisInputVO.y())
                      .build();
    }
}
