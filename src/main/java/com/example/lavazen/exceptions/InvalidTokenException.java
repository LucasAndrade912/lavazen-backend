package com.example.lavazen.exceptions;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException() {
        super("Token is invalid");
    }

    public InvalidTokenException(String message) {
        super(message);
    }
}
