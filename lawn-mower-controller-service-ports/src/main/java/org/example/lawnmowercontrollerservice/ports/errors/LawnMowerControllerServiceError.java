package org.example.lawnmowercontrollerservice.ports.errors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum LawnMowerControllerServiceError {
    // Internal errors
    UNEXPECTED_ERROR("E5000", "An unexpected error has occured");
    private final String errorCode;
    private final String errorMessage;
}
