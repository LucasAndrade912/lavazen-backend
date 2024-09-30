package com.example.lavazen.services;

import com.example.lavazen.dtos.RequestCreateWashingDTO;
import com.example.lavazen.dtos.ResponseCreateWashingDTO;
import com.example.lavazen.dtos.ResponseWashingDTO;
import com.example.lavazen.models.Washing;
import com.example.lavazen.repositories.WashingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WashingService {
    @Autowired
    private WashingRepository repository;

    public List<ResponseWashingDTO> getAll() {
        return this.repository.findAll().stream().map(ResponseWashingDTO::new).toList();
    }

    public Optional<ResponseWashingDTO> getById(Long id) {
        return this.repository.findById(id).map(ResponseWashingDTO::new);
    }

    public ResponseCreateWashingDTO create(RequestCreateWashingDTO dto) {
        Washing washing = new Washing(dto.name(), dto.description(), dto.duration(), dto.price());
        this.repository.save(washing);
        return new ResponseCreateWashingDTO(washing.getId(), washing.getName());
    }

    public void delete(Long id) {
        Washing washing = this.repository.findById(id).orElseThrow();
        this.repository.delete(washing);
    }
}
