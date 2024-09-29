package com.example.lavazen.repositories;

import com.example.lavazen.models.Washing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WashingRepository extends JpaRepository<Washing, Long> {
}
