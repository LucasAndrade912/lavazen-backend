package com.example.lavazen.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "washing")
public class Washing {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom_washing_sequence")
    @SequenceGenerator(name = "custom_washing_sequence", sequenceName = "washing_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Integer duration; // In seconds

    @Column(nullable = false)
    private Double price;

    @OneToMany(mappedBy = "washing", cascade = CascadeType.ALL)
    private List<CarWashBooking> bookings;

    public Washing(String name, String description, Integer duration, Double price) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.price = price;
    }

    public Washing() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<CarWashBooking> getBookings() {
        return bookings;
    }

    public void setBookings(List<CarWashBooking> bookings) {
        this.bookings = bookings;
    }
}
