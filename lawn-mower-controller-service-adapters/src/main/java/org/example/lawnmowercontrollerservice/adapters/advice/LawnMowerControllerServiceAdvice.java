package org.example.lawnmowercontrollerservice.adapters.advice;

import lombok.extern.log4j.Log4j2;
import org.example.lawnmowercontrollerservice.ports.errors.LawnMowerControllerServiceError;
import org.example.lawnmowercontrollerservice.ports.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
@Log4j2
public class LawnMowerControllerServiceAdvice {

    @ExceptionHandler(UpperRightAxisNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Mono<ErrorResponseDTO> notFoundErrorException(LawnMowerControllerServiceException e) {
        log.error("A not found error has occurred: ", e);
        return Mono.just(new ErrorResponseDTO(e.getErrorCode(), e.getErrorMessage()));
    }

    @ExceptionHandler({InputLinesHasIncorrectLengthException.class, WrongCoordinatesValueException.class, WrongDirectionCharacterException.class,
                       WrongInstructionValueException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Mono<ErrorResponseDTO> badRequestErrorException(LawnMowerControllerServiceException e) {
        log.error("A bad request error has occurred: ", e);
        return Mono.just(new ErrorResponseDTO(e.getErrorCode(), e.getErrorMessage()));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    Mono<ErrorResponseDTO> unexpectedErrorException(Exception e) {
        log.error("An unexpected error has occurred: ", e);
        LawnMowerControllerServiceError unexpectedError = LawnMowerControllerServiceError.UNEXPECTED_ERROR;
        return Mono.just(new ErrorResponseDTO(unexpectedError.getErrorCode(), unexpectedError.getErrorMessage()));
    }
}
