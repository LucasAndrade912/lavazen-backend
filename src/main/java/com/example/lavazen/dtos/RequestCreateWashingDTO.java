package com.example.lavazen.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.Duration;

public record RequestCreateWashingDTO(
        @NotBlank(message = "Name must not be blank")
        @Size(min = 4, max = 30, message = "Name must be between 4 and 30 characters")
        String name,

        @NotBlank(message = "Description is required")
        String description,

        @NotNull(message = "Duration must not be null")
        @Positive(message = "Duration must be greater than 0")
        Duration duration,

        @NotNull(message = "Price must not be null")
        @Positive(message = "Price must be greater than 0")
        Double price
) {
}
