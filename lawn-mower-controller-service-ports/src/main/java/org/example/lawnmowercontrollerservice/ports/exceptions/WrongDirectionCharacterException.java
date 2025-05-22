package org.example.lawnmowercontrollerservice.ports.exceptions;

import org.example.lawnmowercontrollerservice.ports.errors.LawnMowerControllerServiceError;

public class WrongDirectionCharacterException extends LawnMowerControllerServiceException {

    public WrongDirectionCharacterException() {
        super(LawnMowerControllerServiceError.WRONG_DIRECTION_CHARACTER_ERROR);
    }
}
