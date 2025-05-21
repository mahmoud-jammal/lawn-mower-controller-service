package org.example.lawnmowercontrollerservice.core.mappers;

import lombok.RequiredArgsConstructor;
import org.example.lawnmowercontrollerservice.core.dtos.AxisDTO;
import org.example.lawnmowercontrollerservice.core.dtos.CoordinatesDTO;
import org.example.lawnmowercontrollerservice.core.enums.Direction;
import org.example.lawnmowercontrollerservice.core.models.MowerModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class MowerModelMapper {

    private static final String INSTRUCTIONS_REGEX = "[A-Z]+";
    private static final String COORDINATES_REGEX = "\\d\\s\\d\\s[A-Z]";

    public List<MowerModel> mapToMowerModelList(List<String> inputLines) {
        return IntStream.range(1, inputLines.size())
                        .filter(i -> i % 2 != 0)
                        .mapToObj(i -> {
                            String coordinatesLine = inputLines.get(i).toUpperCase();
                            CoordinatesDTO coordinatesDTO = getCoordinatesDTO(coordinatesLine);
                            String instructionLine = inputLines.get(i + 1).toUpperCase();
                            validateInstructionsSyntax(instructionLine);
                            return MowerModel.builder()
                                             .coordinatesDTO(coordinatesDTO)
                                             .instructions(instructionLine)
                                             .build();
                        })
                        .toList();
    }

    private void validateInstructionsSyntax(String instructionsValue) {
        Optional.of(instructionsValue)
                .filter(instructions -> instructions.matches(INSTRUCTIONS_REGEX))
                .orElseThrow();
    }

    private CoordinatesDTO getCoordinatesDTO(String coordinatesValue) {
        return Optional.of(coordinatesValue)
                       .filter(coord -> coord.matches(COORDINATES_REGEX))
                       .map(coord -> CoordinatesDTO.builder()
                                                   .axisDTO(getAxisDTO(coord))
                                                   .direction(Direction.fromString(coord.split(" ")[2]))
                                                   .build())
                       .orElseThrow();
    }

    private static AxisDTO getAxisDTO(String s) {
        String[] axisValues = s.split(" ");
        return AxisDTO.builder().x(Integer.parseInt(axisValues[0])).y(Integer.parseInt(axisValues[1])).build();
    }
}
