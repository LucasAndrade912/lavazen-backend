package com.example.lavazen.dtos;

import org.springframework.http.HttpStatus;

public record ErrorMessageDTO(HttpStatus httpStatus, String message) {
}
