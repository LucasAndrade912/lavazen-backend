package com.example.lavazen.controllers;

import com.example.lavazen.dtos.RequestCreateWashingDTO;
import com.example.lavazen.dtos.ResponseCreateWashingDTO;
import com.example.lavazen.dtos.ResponseWashingDTO;
import com.example.lavazen.services.WashingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/washings")
@CrossOrigin
public class WashingController {
    @Autowired
    private WashingService service;

    @GetMapping
    public List<ResponseWashingDTO> getAll() {
        return this.service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<ResponseWashingDTO> getById(@PathVariable("id") Long washingId) {
        return this.service.getById(washingId);
    }

    @PostMapping
    public ResponseEntity<ResponseCreateWashingDTO> createWashing(@RequestBody @Valid RequestCreateWashingDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.create(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long washingId) {
        System.out.println(washingId);
        this.service.delete(washingId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
