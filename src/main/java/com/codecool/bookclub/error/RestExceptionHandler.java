package com.codecool.bookclub.error;

import jakarta.validation.ConstraintViolation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleValidationException(MethodArgumentNotValidException ex) {

        ErrorDto dto = new ErrorDto(HttpStatus.BAD_REQUEST, "Validation error");

        dto.setDetailedMessages(ex.getBindingResult().getAllErrors().stream()
                .map(err -> err.unwrap(ConstraintViolation.class))
                .map(err -> String.format("%s: %s", err.getPropertyPath(), err.getMessage()))
                .collect(Collectors.toList()));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleLoginException(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Niepoprawne dane logowania");
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<String> handleDisabledUserException(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Twoje konto jest nieaktywne");
    }
}
