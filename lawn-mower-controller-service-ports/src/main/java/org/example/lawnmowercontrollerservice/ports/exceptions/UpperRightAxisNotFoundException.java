package org.example.lawnmowercontrollerservice.ports.exceptions;

import org.example.lawnmowercontrollerservice.ports.errors.LawnMowerControllerServiceError;

public class UpperRightAxisNotFoundException extends LawnMowerControllerServiceException {

    public UpperRightAxisNotFoundException() {
        super(LawnMowerControllerServiceError.UPPER_RIGHT_AXIS_NOT_FOUND_ERROR);
    }
}
