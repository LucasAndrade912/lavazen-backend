package com.example.lavazen.controllers;

import com.example.lavazen.dtos.RequestLoginDTO;
import com.example.lavazen.dtos.RequestRegisterUserDTO;
import com.example.lavazen.dtos.ResponseLoginDTO;
import com.example.lavazen.dtos.ResponseRegisterUserDTO;
import com.example.lavazen.repositories.UserRepository;
import com.example.lavazen.services.AuthorizationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorizationService authorizationService;

    @PostMapping("/login")
    public ResponseEntity<ResponseLoginDTO> login(@RequestBody @Valid RequestLoginDTO requestLoginDTO) {
        ResponseLoginDTO responseLoginDTO = this.authorizationService.login(requestLoginDTO);
        return ResponseEntity.status(HttpStatus.OK).body(responseLoginDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseRegisterUserDTO> register(
            @RequestBody @Valid RequestRegisterUserDTO requestRegisterUserDTO
    ) throws Exception {
        ResponseRegisterUserDTO registerUserDTO = this.authorizationService.register(requestRegisterUserDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(registerUserDTO);
    }
}
