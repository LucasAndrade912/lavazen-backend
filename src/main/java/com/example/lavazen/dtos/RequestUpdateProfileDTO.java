package com.example.lavazen.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RequestUpdateProfileDTO(
        @NotBlank(message = "Name must not be blank")
        @Size(min = 4, max = 50, message = "Name must be between 4 and 50 characters")
        String name,

        @Pattern(regexp = "\\+?[0-9]{10,15}", message = "Phone number must be valid")
        String phone,

        @NotBlank(message = "Birthday must not be blank")
        String birthDay,

        String address
) {
}
