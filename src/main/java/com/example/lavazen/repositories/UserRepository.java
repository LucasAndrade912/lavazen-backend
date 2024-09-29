package com.example.lavazen.repositories;

import com.example.lavazen.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
