package com.example.lavazen.exceptions;

public class UserUnderAgeException extends RuntimeException {
    public UserUnderAgeException() {
        super("User must be at least 18 years old");
    }

    public UserUnderAgeException(String message) {
        super(message);
    }
}
