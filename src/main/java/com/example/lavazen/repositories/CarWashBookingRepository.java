package com.example.lavazen.repositories;

import com.example.lavazen.models.CarWashBooking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CarWashBookingRepository extends JpaRepository<CarWashBooking, Long> {
    List<CarWashBooking> findByDate(LocalDate date);
}
