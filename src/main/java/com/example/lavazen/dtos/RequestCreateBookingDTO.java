package com.example.lavazen.dtos;

import com.example.lavazen.models.PaymentMethod;
import jakarta.validation.constraints.NotNull;

public record RequestCreateBookingDTO(
        @NotNull(message = "Washing ID is required")
        Long washingId,

        @NotNull(message = "Car plate is required")
        String carPlate,

        @NotNull(message = "Car plate is required")
        String carModel,

        @NotNull(message = "Payment method is required")
        PaymentMethod paymentMethod,

        @NotNull(message = "Date is required")
        String date,

        @NotNull(message = "Start hour is required")
        String startHour
) {
}
