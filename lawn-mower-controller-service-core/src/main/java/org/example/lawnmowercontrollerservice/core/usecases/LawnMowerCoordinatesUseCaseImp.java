package org.example.lawnmowercontrollerservice.core.usecases;

import lombok.RequiredArgsConstructor;
import org.example.lawnmowercontrollerservice.core.dtos.AxisDTO;
import org.example.lawnmowercontrollerservice.core.mappers.MowerModelMapper;
import org.example.lawnmowercontrollerservice.core.models.MowerModel;
import org.example.lawnmowercontrollerservice.ports.inbound.LawnMowerCoordinatesUseCase;
import org.example.lawnmowercontrollerservice.ports.vo.requests.UpdateCoordinatesRequestVO;
import org.example.lawnmowercontrollerservice.ports.vo.responses.UpdateCoordinatesResponseVO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LawnMowerCoordinatesUseCaseImp implements LawnMowerCoordinatesUseCase {

    private static final String AXIS_REGEX = "\\d\\s\\d";

    private final MowerModelMapper mowerModelMapper;

    @Override
    public Mono<UpdateCoordinatesResponseVO> updateLawnMowerCoordinates(UpdateCoordinatesRequestVO updateCoordinatesRequestVO) {
        List<String> inputLines = updateCoordinatesRequestVO.inputLines();
        AxisDTO upperRightAxis = getUpperRightAxis(inputLines);
        inputLines = removeBlankLines(inputLines);
        validateInputLines(inputLines);

        return Mono.just(mapToUpdateCoordinatesResponseVO(inputLines, upperRightAxis));
    }

    private UpdateCoordinatesResponseVO mapToUpdateCoordinatesResponseVO(List<String> inputLines, AxisDTO upperRightAxis) {
        return new UpdateCoordinatesResponseVO(getUpdatedMowersCoordinates(mowerModelMapper.mapToMowerModelList(inputLines), upperRightAxis));
    }

    private AxisDTO getUpperRightAxis(List<String> inputLines) {
        Optional<String> upperRight = inputLines.stream().findFirst();
        return upperRight.filter(upperRightValue -> upperRightValue.matches(AXIS_REGEX))
                         .map(LawnMowerCoordinatesUseCaseImp::getAxisDTO)
                         .orElseThrow();
    }

    private List<String> getUpdatedMowersCoordinates(List<MowerModel> mowerModels, AxisDTO upperRightAxis) {
        return mowerModels.stream().map(mowerModel -> mowerModel.executeInstructions(upperRightAxis)).toList();
    }

    private void validateInputLines(List<String> inputLines) {
        if ((inputLines.size() - 1) % 2 != 0) {
            throw new IllegalArgumentException("Invalid input: each mower must have a position and instruction line.");
        }
    }

    private List<String> removeBlankLines(List<String> inputLines) {
        return inputLines.stream().filter(line -> !line.isBlank()).toList();
    }

    private static AxisDTO getAxisDTO(String s) {
        String[] axisValues = s.split(" ");
        return AxisDTO.builder().x(Integer.parseInt(axisValues[0])).y(Integer.parseInt(axisValues[1])).build();
    }
}
