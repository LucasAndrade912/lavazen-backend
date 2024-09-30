package com.example.lavazen.dtos;

public record RequestCreateBookingDTO(
        Long washingId,
        String date,
        String startHour
) {
}
