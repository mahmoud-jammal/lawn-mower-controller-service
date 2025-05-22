package org.example.lawnmowercontrollerservice.ports.exceptions;

import org.example.lawnmowercontrollerservice.ports.errors.LawnMowerControllerServiceError;

public class InputLinesHasIncorrectLengthException extends LawnMowerControllerServiceException {

    public InputLinesHasIncorrectLengthException() {
        super(LawnMowerControllerServiceError.INPUT_LINES_HAS_INCORRECT_LENGTH_ERROR);
    }
}
