package com.jedox.bidemo.application;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestControllerAdvice {

    @ExceptionHandler(value = EntityNotFoundException.class)
    protected ResponseEntity<String> handleNotFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity not found!");
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    protected ResponseEntity<String> handleConstraintViolationException() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong parameters!");
    }
}
