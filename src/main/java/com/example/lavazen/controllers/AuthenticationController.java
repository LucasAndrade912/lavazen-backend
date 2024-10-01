package com.example.lavazen.controllers;

import com.example.lavazen.dtos.*;
import com.example.lavazen.models.User;
import com.example.lavazen.repositories.UserRepository;
import com.example.lavazen.services.AuthorizationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
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

    @GetMapping("/profile")
    public ResponseEntity<ResponseProfileDTO> profile(
            @AuthenticationPrincipal User user
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(this.authorizationService.profile(user));
    }

    @PatchMapping("/profile")
    public ResponseEntity<ResponseProfileDTO> updateProfile(
            @AuthenticationPrincipal User user,
            @RequestBody @Valid RequestUpdateProfileDTO requestUpdateProfileDTO
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(this.authorizationService.updateProfile(user, requestUpdateProfileDTO));
    }

    @DeleteMapping("/profile")
    public ResponseEntity deleteProfile(
            @AuthenticationPrincipal User user
    ) {
        this.authorizationService.deleteProfile(user);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
