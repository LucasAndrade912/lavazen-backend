package com.example.lavazen.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RequestRegisterUserDTO(
        @NotBlank(message = "Name must not be blank")
        @Size(min = 4, max = 50, message = "Name must be between 4 and 50 characters")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Email should be in a valid format")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 8, max = 30, message = "Password must be between 8 and 30 characters")
        String password,

        @NotBlank(message = "Birthday is required")
        String birthDay
) {
}
