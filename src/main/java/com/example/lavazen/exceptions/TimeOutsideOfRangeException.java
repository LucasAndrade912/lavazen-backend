package com.example.lavazen.exceptions;

public class TimeOutsideOfRangeException extends RuntimeException {
    public TimeOutsideOfRangeException() {
        super("Time outside the range of 08:00 and 17:00");
    }

    public TimeOutsideOfRangeException(String message) {
        super(message);
    }
}
