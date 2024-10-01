package com.example.lavazen.dtos;

import com.example.lavazen.models.PaymentMethod;
import com.example.lavazen.models.User;

public record CreateBookingDTO(
        User user,
        Long washingId,
        String carModel,
        String carPlate,
        PaymentMethod paymentMethod,
        String date,
        String startHour
) {
}
