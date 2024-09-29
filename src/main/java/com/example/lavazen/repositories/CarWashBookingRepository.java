package com.example.lavazen.repositories;

import com.example.lavazen.models.CarWashBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarWashBookingRepository extends JpaRepository<CarWashBooking, Long> {
}
