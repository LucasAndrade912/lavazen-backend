package com.example.lavazen.dtos;

import com.example.lavazen.models.CarWashBooking;

import java.time.Duration;
import java.time.format.DateTimeFormatter;

public record ResponseCreateBookingDTO(
        Long id,
        String userEmail,
        String date,
        String startHour,
        String carModel,
        String paymentMethod,
        String washingName,
        Double washingPrice,
        Long washingDuration
) {
    static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public ResponseCreateBookingDTO(CarWashBooking booking) {
        this(
                booking.getId(),
                booking.getUser().getEmail(),
                booking.getDate().format(dateFormatter),
                booking.getStartHour().toString(),
                booking.getCarModel(),
                booking.getPaymentMethod().getMethod(),
                booking.getWashing().getName(),
                booking.getWashing().getPrice(),
                booking.getWashing().getDuration().toMinutes()
        );
    }
}
