package com.example.lavazen.dtos;

import com.example.lavazen.models.CarWashBooking;

import java.time.format.DateTimeFormatter;

public record ResponseBookingDTO(
        Long id,
        String date,
        String startHour,
        String userName,
        String userEmail,
        String userPhone,
        String washingName
) {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public ResponseBookingDTO(CarWashBooking booking) {
        this(
                booking.getId(),
                booking.getDate().format(formatter),
                booking.getStartHour().toString(),
                booking.getUser().getName(),
                booking.getUser().getEmail(),
                booking.getUser().getPhone(),
                booking.getWashing().getName()
        );
    }
}
