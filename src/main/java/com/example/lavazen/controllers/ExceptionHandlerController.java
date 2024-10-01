package com.example.lavazen.controllers;

import com.example.lavazen.dtos.ErrorMessageDTO;
import com.example.lavazen.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.format.DateTimeParseException;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(WashingNotFoundException.class)
    private ResponseEntity<ErrorMessageDTO> washingNotFoundHandler(WashingNotFoundException e) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        return ResponseEntity
                .status(httpStatus)
                .body(new ErrorMessageDTO(httpStatus, e.getMessage()));
    }

    @ExceptionHandler(DateNotIsAfterNowException.class)
    private ResponseEntity<ErrorMessageDTO> dateNotIsAfterNowHandler(DateNotIsAfterNowException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return ResponseEntity
                .status(httpStatus)
                .body(new ErrorMessageDTO(httpStatus, e.getMessage()));
    }

    @ExceptionHandler(TimeNotExactException.class)
    private ResponseEntity<ErrorMessageDTO> timeNotExactHandler(TimeNotExactException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return ResponseEntity
                .status(httpStatus)
                .body(new ErrorMessageDTO(httpStatus, e.getMessage()));
    }

    @ExceptionHandler(TimeOutsideOfRangeException.class)
    private ResponseEntity<ErrorMessageDTO> timeOutsideOfRangeHandler(TimeOutsideOfRangeException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return ResponseEntity
                .status(httpStatus)
                .body(new ErrorMessageDTO(httpStatus, e.getMessage()));
    }

    @ExceptionHandler(DateTimeParseException.class)
    private ResponseEntity<ErrorMessageDTO> dateTimeParseHandler(DateTimeParseException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return ResponseEntity
                .status(httpStatus)
                .body(new ErrorMessageDTO(httpStatus, e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ErrorMessageDTO> methodArgumentNotValidHandler(MethodArgumentNotValidException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return ResponseEntity
                .status(httpStatus)
                .body(new ErrorMessageDTO(httpStatus, e.getBindingResult().getFieldError().getDefaultMessage()));
    }

    @ExceptionHandler(InvalidTokenException.class)
    private ResponseEntity<ErrorMessageDTO> invalidTokenHandler(InvalidTokenException e) {
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        return ResponseEntity
                .status(httpStatus)
                .body(new ErrorMessageDTO(httpStatus, e.getMessage()));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    private ResponseEntity<ErrorMessageDTO> userAlreadyExistsHandler(UserAlreadyExistsException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return ResponseEntity
                .status(httpStatus)
                .body(new ErrorMessageDTO(httpStatus, e.getMessage()));
    }

    @ExceptionHandler(UserUnderAgeException.class)
    private ResponseEntity<ErrorMessageDTO> userUnderAgeHandler(UserUnderAgeException e) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        return ResponseEntity
                .status(httpStatus)
                .body(new ErrorMessageDTO(httpStatus, e.getMessage()));
    }
}
