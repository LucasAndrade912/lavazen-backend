package com.example.lavazen.exceptions;

public class WashingNotFoundException extends RuntimeException {
    public WashingNotFoundException() {
        super("Washing not found");
    }

    public WashingNotFoundException(String message) {
        super(message);
    }
}
