package com.example.lavazen.controllers;

import com.example.lavazen.dtos.CreateBookingDTO;
import com.example.lavazen.dtos.RequestCreateBookingDTO;
import com.example.lavazen.dtos.ResponseBookingDTO;
import com.example.lavazen.dtos.ResponseCreateBookingDTO;
import com.example.lavazen.models.User;
import com.example.lavazen.services.CarWashingBookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@CrossOrigin
public class CarWashingBookingController {
    @Autowired
    private CarWashingBookingService service;

    @PostMapping
    public ResponseEntity<ResponseCreateBookingDTO> createBooking(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid RequestCreateBookingDTO bookingDTO
    ) throws Exception {
        CreateBookingDTO dto = new CreateBookingDTO(user, bookingDTO.washingId(), bookingDTO.date(), bookingDTO.startHour());
        return ResponseEntity.status(HttpStatus.CREATED).body(this.service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<ResponseBookingDTO>> getAllBookings() {
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/availabletimes")
    public ResponseEntity<List<String>> getAvailableTimes(
            @RequestParam Long washingId,
            @RequestParam String date
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.getAvailableTimes(washingId, date));
    }
}
