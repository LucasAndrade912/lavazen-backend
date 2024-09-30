package com.example.lavazen.dtos;

import com.example.lavazen.models.User;
import com.example.lavazen.models.UserRole;

import java.time.format.DateTimeFormatter;

public record ResponseRegisterUserDTO(
        Long id,
        String name,
        String email,
        String password,
        UserRole role,
        String birthDay
) {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public ResponseRegisterUserDTO(User user) {
        this(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole(),
                user.getBirthDay().format(formatter)
        );
    }
}
