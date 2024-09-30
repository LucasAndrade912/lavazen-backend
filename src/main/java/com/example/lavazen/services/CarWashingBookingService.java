package com.example.lavazen.services;

import com.example.lavazen.dtos.CreateBookingDTO;
import com.example.lavazen.dtos.ResponseBookingDTO;
import com.example.lavazen.dtos.ResponseCreateBookingDTO;
import com.example.lavazen.models.CarWashBooking;
import com.example.lavazen.models.Washing;
import com.example.lavazen.repositories.CarWashBookingRepository;
import com.example.lavazen.repositories.UserRepository;
import com.example.lavazen.repositories.WashingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CarWashingBookingService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WashingRepository washingRepository;

    @Autowired
    private CarWashBookingRepository carWashBookingRepository;

    public ResponseCreateBookingDTO create(CreateBookingDTO createBookingDTO) {
        Washing washing = this.washingRepository.findById(createBookingDTO.washingId()).orElseThrow();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate date = LocalDate.parse(createBookingDTO.date(), formatter);
        LocalTime startHour = LocalTime.parse(createBookingDTO.startHour());

        CarWashBooking booking = new CarWashBooking(date, startHour, createBookingDTO.user(), washing);
        CarWashBooking bookingSaved = this.carWashBookingRepository.save(booking);

        return new ResponseCreateBookingDTO(bookingSaved);
    }

    public List<ResponseBookingDTO> getAll() {
        return this.carWashBookingRepository.findAll().stream().map(ResponseBookingDTO::new).toList();
    }
}
