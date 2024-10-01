package com.example.lavazen.exceptions;

public class DateNotIsAfterNowException extends RuntimeException {
    public DateNotIsAfterNowException() {
        super("Date must be after the current date");
    }

    public DateNotIsAfterNowException(String message) {
        super(message);
    }
}
