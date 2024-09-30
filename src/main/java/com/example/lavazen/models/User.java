package com.example.lavazen.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "custom_user_sequence")
    @SequenceGenerator(name = "custom_user_sequence", sequenceName = "user_seq", allocationSize = 1)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String password;

    @Column(length = 13)
    private String phone;

    @Column(nullable = false)
    private LocalDate birthDay;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<CarWashBooking> bookings;

    private String address;
    private UserRole role;

    public User(String name, String email, String password, LocalDate birthDay, String address, String phone, UserRole role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.birthDay = birthDay;
        this.address = address;
        this.phone = phone;
        this.role = role;
    }

    public User() {
    }

    public User(String name, String email, String encryptedPassword, UserRole role, LocalDate birthDay) {
        this.name = name;
        this.email = email;
        this.password = encryptedPassword;
        this.role = role;
        this.birthDay = birthDay;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.EMPLOYEE)
            return List.of(
                    new SimpleGrantedAuthority("ROLE_EMPLOYEE"),
                    new SimpleGrantedAuthority("ROLE_CUSTOMER")
            );
        else
            return List.of(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
    }

    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<CarWashBooking> getBookings() {
        return bookings;
    }

    public void setBookings(List<CarWashBooking> bookings) {
        this.bookings = bookings;
    }
}
