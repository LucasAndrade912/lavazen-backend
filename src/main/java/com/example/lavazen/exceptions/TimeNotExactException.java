package com.example.lavazen.exceptions;

public class TimeNotExactException extends RuntimeException {
    public TimeNotExactException() {
        super("Time is not exact time");
    }

    public TimeNotExactException(String message) {
        super(message);
    }
}
