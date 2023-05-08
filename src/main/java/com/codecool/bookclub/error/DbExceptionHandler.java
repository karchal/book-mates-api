package com.codecool.bookclub.error;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DbExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDto> handleException(ConstraintViolationException ex) {
        ErrorDto dto = new ErrorDto(HttpStatus.BAD_REQUEST, "Validation error");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }
}
