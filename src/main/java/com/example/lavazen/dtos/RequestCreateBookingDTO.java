package com.example.lavazen.dtos;

import jakarta.validation.constraints.NotNull;

public record RequestCreateBookingDTO(
        @NotNull(message = "Washing ID must not be null")
        Long washingId,

        @NotNull(message = "Date must not be null")
        String date,

        @NotNull(message = "Start hour must not be null")
        String startHour
) {
}
