package net.kickit.hospitalinfoservice.controller;

import lombok.extern.slf4j.Slf4j;
import net.kickit.hospitalinfoservice.exception.FacilityNotFoundException;
import net.kickit.hospitalinfoservice.exception.InvalidInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ExceptionHelper {

    @ExceptionHandler(value = { InvalidInputException.class })
    public ResponseEntity<Object> handleInvalidInputException(InvalidInputException ex) {
        log.error("Invalid Input Exception: {}",ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { FacilityNotFoundException.class })
    public ResponseEntity<Object> handleFacilityNotFoundException(FacilityNotFoundException ex) {
        log.error("Invalid Input Exception: {}",ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<Object> handleGeneralException(Exception ex) {
        log.error("Exception has occurred while processing request: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
