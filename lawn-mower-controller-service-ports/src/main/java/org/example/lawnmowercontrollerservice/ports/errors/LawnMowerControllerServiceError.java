package org.example.lawnmowercontrollerservice.ports.errors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum LawnMowerControllerServiceError {
    // Internal errors
    UNEXPECTED_ERROR("E5000", "An unexpected error has occured"),
    // Not found errors
    UPPER_RIGHT_AXIS_NOT_FOUND_ERROR("E4040", "Upper right axis line is not found or incorrect"),
    // Bad request errors
    INPUT_LINES_HAS_INCORRECT_LENGTH_ERROR("E4000", "Input lines length is incorrect"),
    WRONG_INSTRUCTION_VALUE_ERROR("E4001", "Instruction characters should only be 'L', 'R' or 'F'"),
    WRONG_COORDINATES_VALUE_ERROR("E4002", "Coordinates characters should be of format digit digit letter"),
    WRONG_DIRECTION_CHARACTER_ERROR("E4003", "Direction value should be one of the following letters: 'N', 'E', 'W','S'");

    private final String errorCode;
    private final String errorMessage;
}
