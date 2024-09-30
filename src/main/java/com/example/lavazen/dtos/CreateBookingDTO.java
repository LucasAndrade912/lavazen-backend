package com.example.lavazen.dtos;

import com.example.lavazen.models.User;

public record CreateBookingDTO(
        User user,
        Long washingId,
        String date,
        String startHour
) {
}
