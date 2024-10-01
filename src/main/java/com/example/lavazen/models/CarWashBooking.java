package com.example.lavazen.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "car_wash_bookings")
public class CarWashBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom_wash_booking_sequence")
    @SequenceGenerator(name = "custom_wash_booking_sequence", sequenceName = "car_wash_booking_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String carModel;

    @Column(nullable = false, unique = true)
    private String carPlate;

    @Column(nullable = false)
    private PaymentMethod paymentMethod;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalTime startHour;

    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    private User user;

    @ManyToOne(cascade = CascadeType.REFRESH, optional = false)
    private Washing washing;

    public CarWashBooking(String carModel, String carPlate, PaymentMethod paymentMethod, LocalDate date, LocalTime startHour, User user, Washing washing) {
        this.carModel = carModel;
        this.carPlate = carPlate;
        this.paymentMethod = paymentMethod;
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

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
