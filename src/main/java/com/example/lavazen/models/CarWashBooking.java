package com.example.lavazen.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "car_wash_bookings")
public class CarWashBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom_sequence")
    @SequenceGenerator(name = "custom_sequence", sequenceName = "car_wash_booking_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime startHour;

    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    private User user;

    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    private Washing washing;

    public CarWashBooking(LocalDate date, LocalTime startHour, User user, Washing washing) {
        this.date = date;
        this.startHour = startHour;
        this.user = user;
        this.washing = washing;
    }

    public CarWashBooking() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartHour() {
        return startHour;
    }

    public void setStartHour(LocalTime startHour) {
        this.startHour = startHour;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Washing getWashing() {
        return washing;
    }

    public void setWashing(Washing washing) {
        this.washing = washing;
    }
}
