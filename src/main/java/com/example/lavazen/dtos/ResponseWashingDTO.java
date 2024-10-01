package com.example.lavazen.dtos;

import com.example.lavazen.models.Washing;

public record ResponseWashingDTO(
        Long id,
        String name,
        String description,
        Double price,
        Long duration
) {
    public ResponseWashingDTO(Washing washing) {
        this(washing.getId(),
                washing.getName(),
                washing.getDescription(),
                washing.getPrice(),
                washing.getDuration().toMinutes());
    }
}
