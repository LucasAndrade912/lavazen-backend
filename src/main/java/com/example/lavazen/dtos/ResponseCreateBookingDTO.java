package com.example.lavazen.dtos;

import com.example.lavazen.models.CarWashBooking;

import java.time.format.DateTimeFormatter;

public record ResponseCreateBookingDTO(
        Long id,
        String date,
        String startHour,
        String washingName,
        Double washingPrice,
        Integer washingDuration
) {
    static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public ResponseCreateBookingDTO(CarWashBooking booking) {
        this(
                booking.getId(),
                booking.getDate().format(dateFormatter),
                booking.getStartHour().toString(),
                booking.getWashing().getName(),
                booking.getWashing().getPrice(),
                booking.getWashing().getDuration()
        );
    }
}
