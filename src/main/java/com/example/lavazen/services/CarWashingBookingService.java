package com.example.lavazen.services;

import com.example.lavazen.dtos.CreateBookingDTO;
import com.example.lavazen.dtos.ResponseBookingDTO;
import com.example.lavazen.dtos.ResponseCreateBookingDTO;
import com.example.lavazen.exceptions.*;
import com.example.lavazen.models.CarWashBooking;
import com.example.lavazen.models.Washing;
import com.example.lavazen.repositories.CarWashBookingRepository;
import com.example.lavazen.repositories.UserRepository;
import com.example.lavazen.repositories.WashingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

        LocalTime endHour = startHour.plus(washing.getDuration());
        if (startHour.isBefore(LocalTime.of(8, 0)) || endHour.isAfter(LocalTime.of(18, 0))) {
            throw new OutsideOpeningHoursException();
        }

        List<CarWashBooking> bookings = this.carWashBookingRepository.findByDate(date);
        bookings.forEach(booking -> {
            if (this.isConflicting(startHour, endHour, booking)) {
                throw new BusyBookingException();
            }
        });

        CarWashBooking booking = new CarWashBooking(createBookingDTO.carModel(), createBookingDTO.carPlate(), createBookingDTO.paymentMethod(), date, startHour, createBookingDTO.user(), washing);
        CarWashBooking bookingSaved = this.carWashBookingRepository.save(booking);

        return new ResponseCreateBookingDTO(bookingSaved);
    }

    public List<ResponseBookingDTO> getAll() {
        return this.carWashBookingRepository.findAll().stream().map(ResponseBookingDTO::new).toList();
    }

    public List<String> getAvailableTimes(Long washingId, String date) {
        Washing washing = this.washingRepository.findById(washingId).orElseThrow(WashingNotFoundException::new);
        Duration duration = washing.getDuration();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate dateFormatted = LocalDate.parse(date, dateFormatter);

        LocalTime openingTime = LocalTime.of(8, 0);
        LocalTime closingTime = LocalTime.of(18, 0);

        List<CarWashBooking> bookings = this.carWashBookingRepository.findByDate(dateFormatted);
        List<String> availableTimes = new ArrayList<>();

        // Iteração durante intervalos de 1 hora entre 8h até 18h
        LocalTime startInterval = openingTime;
        while (!startInterval.plus(duration).isAfter(closingTime)) {
            LocalTime endInterval = startInterval.plus(duration);

            LocalTime finalStartInterval = startInterval;
            //Verifica se tem conflito
            boolean isConflited = bookings.stream().anyMatch(booking -> this.isConflicting(finalStartInterval, endInterval, booking));

            // Caso não tenha conflito de horário adiciona na lista de horário disponíveis
            if (!isConflited) {
                availableTimes.add(startInterval.toString());
            }

            startInterval = startInterval.plusHours(1);
        }

        return availableTimes;
    }

    private boolean isConflicting(LocalTime startInterval, LocalTime endInterval, CarWashBooking booking) {
        LocalTime bookingStartHour = booking.getStartHour();
        LocalTime bookingEndHour = bookingStartHour.plus(booking.getWashing().getDuration());

        // Retorna true se houver interseção nos horários
        return startInterval.isBefore(bookingEndHour) && endInterval.isAfter(bookingStartHour);
    }
}
