package org.example.lawnmowercontrollerservice.adapters.validators;

import org.example.lawnmowercontrollerservice.ports.exceptions.InputLinesHasIncorrectLengthException;
import org.example.lawnmowercontrollerservice.ports.exceptions.WrongInstructionValueException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UpdateCoordinatesRequestValidator {
    private static final String INSTRUCTIONS_REGEX = "[A-Z]+";

    public void validateInstructionsSyntax(String instructionsValue) {
        Optional.of(instructionsValue)
                .filter(instructions -> instructions.matches(INSTRUCTIONS_REGEX))
                .orElseThrow(WrongInstructionValueException::new);
    }

    public void validateInputLinesLength(List<String> inputLines) {
        if ((inputLines.size() - 1) % 2 != 0) {
            throw new InputLinesHasIncorrectLengthException();
        }
    }
}
