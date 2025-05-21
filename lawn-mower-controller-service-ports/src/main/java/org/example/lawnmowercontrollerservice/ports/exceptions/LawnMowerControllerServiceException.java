package org.example.lawnmowercontrollerservice.ports.exceptions;

import lombok.Getter;
import org.example.lawnmowercontrollerservice.ports.errors.LawnMowerControllerServiceError;

@Getter
public class LawnMowerControllerServiceException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;

    public LawnMowerControllerServiceException(LawnMowerControllerServiceError serviceError) {
        super(serviceError.getErrorMessage());
        errorCode = serviceError.getErrorCode();
        errorMessage = serviceError.getErrorMessage();
    }
}
