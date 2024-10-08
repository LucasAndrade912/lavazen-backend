package com.example.lavazen.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RequestLoginDTO(
        @NotBlank(message = "Email is required")
        @Email(message = "Email should be in a valid format")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 8, max = 30, message = "Password must be between 8 and 30 characters")
        String password
) {
}
