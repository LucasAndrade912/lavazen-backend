package com.example.lavazen.services;

import com.example.lavazen.dtos.CreateBookingDTO;
import com.example.lavazen.dtos.ResponseBookingDTO;
import com.example.lavazen.dtos.ResponseCreateBookingDTO;
import com.example.lavazen.exceptions.DateNotIsAfterNowException;
import com.example.lavazen.exceptions.TimeNotExactException;
import com.example.lavazen.exceptions.TimeOutsideOfRangeException;
import com.example.lavazen.exceptions.WashingNotFoundException;
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

    public ResponseCreateBookingDTO create(CreateBookingDTO createBookingDTO) throws Exception {
        Washing washing = this.washingRepository
                .findById(createBookingDTO.washingId())
                .orElseThrow(WashingNotFoundException::new);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate date = LocalDate.parse(createBookingDTO.date(), formatter);
        LocalDate currentDate = LocalDate.now();

        // Erro caso a data fornecida não seja posterior a data atual
        if (!date.isAfter(currentDate)) {
            throw new DateNotIsAfterNowException();
        }

        LocalTime startAllowedTime = LocalTime.of(8, 0); // 08:00
        LocalTime endAllowedTime = LocalTime.of(17, 0); // 17:00
        LocalTime startHour = LocalTime.parse(createBookingDTO.startHour());

        // Erro caso não seja fornecido uma hora exata (exemplos: 11:00, 12:00, 15:00)
        if (startHour.getMinute() != 0) {
            throw new TimeNotExactException();
        }

        // Erro caso a hora fornecida não esteja no intervalo entre 8h e 17h
        if (startHour.isBefore(startAllowedTime) || startHour.isAfter(endAllowedTime)) {
            throw new TimeOutsideOfRangeException();
        }

        CarWashBooking booking = new CarWashBooking(date, startHour, createBookingDTO.user(), washing);
        CarWashBooking bookingSaved = this.carWashBookingRepository.save(booking);

        return new ResponseCreateBookingDTO(bookingSaved);
    }

    public List<ResponseBookingDTO> getAll() {
        return this.carWashBookingRepository.findAll().stream().map(ResponseBookingDTO::new).toList();
    }
}
