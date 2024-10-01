package com.example.lavazen.exceptions;

public class BusyBookingException extends RuntimeException {
    public BusyBookingException() {
        super("Time already booked");
    }

    public BusyBookingException(String message) {
        super(message);
    }
}
