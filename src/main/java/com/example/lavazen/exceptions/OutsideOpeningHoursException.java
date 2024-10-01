package com.example.lavazen.exceptions;

public class OutsideOpeningHoursException extends RuntimeException {
    public OutsideOpeningHoursException() {
        super("Hours outside of car wash operating hours");
    }

    public OutsideOpeningHoursException(String message) {
        super(message);
    }
}
