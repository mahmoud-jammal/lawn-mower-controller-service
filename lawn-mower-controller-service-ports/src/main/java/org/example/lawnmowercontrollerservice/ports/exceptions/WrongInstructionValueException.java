package org.example.lawnmowercontrollerservice.ports.exceptions;

import org.example.lawnmowercontrollerservice.ports.errors.LawnMowerControllerServiceError;

public class WrongInstructionValueException extends LawnMowerControllerServiceException {

    public WrongInstructionValueException() {
        super(LawnMowerControllerServiceError.WRONG_INSTRUCTION_VALUE_ERROR);
    }
}
