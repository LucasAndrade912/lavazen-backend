package com.example.lavazen.models;

public enum UserRole {
    EMPLOYEE("employee"),
    CUSTOMER("customer");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
