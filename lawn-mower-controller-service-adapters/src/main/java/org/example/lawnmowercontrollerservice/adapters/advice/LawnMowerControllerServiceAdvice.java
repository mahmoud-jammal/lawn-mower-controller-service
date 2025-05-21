package org.example.lawnmowercontrollerservice.adapters.advice;

import lombok.extern.log4j.Log4j2;
import org.example.lawnmowercontrollerservice.ports.errors.LawnMowerControllerServiceError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@RestControllerAdvice
@Log4j2
public class LawnMowerControllerServiceAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    Mono<ErrorResponseDTO> unexpectedErrorException(Exception e) {
        log.error("An unexpected error has occurred: ", e);
        LawnMowerControllerServiceError unexpectedError = LawnMowerControllerServiceError.UNEXPECTED_ERROR;
        return Mono.just(new ErrorResponseDTO(unexpectedError.getErrorCode(), unexpectedError.getErrorMessage()));
    }
}
