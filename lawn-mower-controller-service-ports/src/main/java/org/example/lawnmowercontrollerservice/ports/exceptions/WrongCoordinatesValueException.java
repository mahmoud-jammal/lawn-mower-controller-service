package org.example.lawnmowercontrollerservice.ports.exceptions;

import org.example.lawnmowercontrollerservice.ports.errors.LawnMowerControllerServiceError;

public class WrongCoordinatesValueException extends LawnMowerControllerServiceException {

    public WrongCoordinatesValueException() {
        super(LawnMowerControllerServiceError.WRONG_COORDINATES_VALUE_ERROR);
    }
}
