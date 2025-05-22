package org.example.lawnmowercontrollerservice.adapters.mappers;

import lombok.RequiredArgsConstructor;
import org.example.lawnmowercontrollerservice.adapters.validators.UpdateCoordinatesRequestValidator;
import org.example.lawnmowercontrollerservice.adapters.vo.requests.UpdateCoordinatesRequestVO;
import org.example.lawnmowercontrollerservice.adapters.vo.responses.UpdateCoordinatesResponseVO;
import org.example.lawnmowercontrollerservice.ports.exceptions.UpperRightAxisNotFoundException;
import org.example.lawnmowercontrollerservice.ports.exceptions.WrongCoordinatesValueException;
import org.example.lawnmowercontrollerservice.ports.vo.inputs.UpdateMowerInputVO;
import org.example.lawnmowercontrollerservice.ports.vo.outputs.UpdateMowerOutputVO;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Component
public class UpdateMowerVOMapper {

    private static final String AXIS_REGEX = "\\d\\s\\d";
    private static final String COORDINATES_REGEX = "\\d\\s\\d\\s[A-Z]";

    private final UpdateCoordinatesRequestValidator updateCoordinatesRequestValidator;

    private static UpdateMowerInputVO.AxisInputVO getAxisInputVO(String s) {
        String[] axisValues = s.split(" ");
        return new UpdateMowerInputVO.AxisInputVO(Integer.parseInt(axisValues[0]), Integer.parseInt(axisValues[1]));
    }

    public UpdateMowerInputVO mapToUpdateMowerInputVO(UpdateCoordinatesRequestVO updateCoordinatesRequestVO) {
        List<String> inputLines = updateCoordinatesRequestVO.inputLines();
        updateCoordinatesRequestValidator.validateInputLinesLength(inputLines);
        inputLines = removeBlankLines(inputLines);
        UpdateMowerInputVO.AxisInputVO upperRightAxis = getUpperRightAxis(inputLines);
        return new UpdateMowerInputVO(upperRightAxis, getMowerInputVOS(inputLines));
    }

    public Mono<UpdateCoordinatesResponseVO> mapToUpdateCoordinatesResponseVO(Mono<UpdateMowerOutputVO> updateMowerOutputMono) {
        return updateMowerOutputMono.map(updateMowerOutputVO ->
                                                 new UpdateCoordinatesResponseVO(updateMowerOutputVO.updatedMowersCoordinates()));
    }

    private List<UpdateMowerInputVO.MowerInputVo> getMowerInputVOS(List<String> inputLines) {
        return IntStream.range(1, inputLines.size())
                        .filter(i -> i % 2 != 0)
                        .mapToObj(i -> {
                            String coordinatesLine = inputLines.get(i).toUpperCase();
                            UpdateMowerInputVO.MowerInputVo.CoordinatesInputVO coordinatesInputVO = getCoordinatesInputVO(coordinatesLine);
                            String instructionLine = inputLines.get(i + 1).toUpperCase();
                            updateCoordinatesRequestValidator.validateInstructionsSyntax(instructionLine);
                            return new UpdateMowerInputVO.MowerInputVo(coordinatesInputVO, instructionLine);
                        })
                        .toList();
    }

    private List<String> removeBlankLines(List<String> inputLines) {
        return inputLines.stream()
                         .filter(line -> !line.isBlank())
                         .toList();
    }

    private UpdateMowerInputVO.AxisInputVO getUpperRightAxis(List<String> inputLines) {
        Optional<String> upperRight = inputLines.stream().findFirst();
        return upperRight.filter(upperRightValue -> upperRightValue.matches(AXIS_REGEX))
                         .map(UpdateMowerVOMapper::getAxisInputVO)
                         .orElseThrow(UpperRightAxisNotFoundException::new);
    }

    private UpdateMowerInputVO.MowerInputVo.CoordinatesInputVO getCoordinatesInputVO(String coordinatesLine) {
        return Optional.of(coordinatesLine)
                       .filter(coord -> coord.matches(COORDINATES_REGEX))
                       .map(coord -> new UpdateMowerInputVO.MowerInputVo.CoordinatesInputVO(getAxisInputVO(coord), coord.split(" ")[2]))
                       .orElseThrow(WrongCoordinatesValueException::new);
    }
}
