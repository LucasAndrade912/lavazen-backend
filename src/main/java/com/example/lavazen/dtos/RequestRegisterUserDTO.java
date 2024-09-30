package com.example.lavazen.dtos;

import com.example.lavazen.models.UserRole;

public record RequestRegisterUserDTO(String name, String email, String password, UserRole role, String birthDay) {
}
