package com.example.lavazen.services;

import com.example.lavazen.dtos.RequestLoginDTO;
import com.example.lavazen.dtos.ResponseLoginDTO;
import com.example.lavazen.dtos.RequestRegisterUserDTO;
import com.example.lavazen.dtos.ResponseRegisterUserDTO;
import com.example.lavazen.models.User;
import com.example.lavazen.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class AuthorizationService implements UserDetailsService {
    @Autowired
    UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.repository.findByEmail(username);
    }

    public ResponseRegisterUserDTO register(RequestRegisterUserDTO userData) throws Exception {
        if (this.repository.findByEmail(userData.email()) != null)
            throw new Exception("User not found.");

        String encryptedPassword = new BCryptPasswordEncoder().encode(userData.password());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        User user = new User(
                userData.name(),
                userData.email(),
                encryptedPassword,
                userData.role(),
                LocalDate.parse(userData.birthDay(), formatter)
        );

        return new ResponseRegisterUserDTO(this.repository.save(user));
    }

    public ResponseLoginDTO login(RequestLoginDTO loginData) {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(
                loginData.email(),
                loginData.password()
        );
        Authentication authenticate = this.authenticationManager.authenticate(usernamePassword);
        String token = this.tokenService.generateToken((User) authenticate.getPrincipal());

        return new ResponseLoginDTO(token);
    }
}
