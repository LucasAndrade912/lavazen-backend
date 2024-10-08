package com.example.lavazen.services;

import com.example.lavazen.dtos.*;
import com.example.lavazen.exceptions.UserAlreadyExistsException;
import com.example.lavazen.exceptions.UserUnderAgeException;
import com.example.lavazen.models.User;
import com.example.lavazen.models.UserRole;
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
        return this.repository.findByEmail(username).get();
    }

    public ResponseRegisterUserDTO register(RequestRegisterUserDTO userData) {
        if (this.repository.findByEmail(userData.email()).isPresent())
            throw new UserAlreadyExistsException();


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate birthdayDate = LocalDate.parse(userData.birthDay(), formatter);
        if (birthdayDate.isAfter(LocalDate.now().minusYears(18))) {
            throw new UserUnderAgeException();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(userData.password());

        User user = new User(
                userData.name(),
                userData.email(),
                encryptedPassword,
                UserRole.CUSTOMER,
                birthdayDate
        );

        String token = this.tokenService.generateToken(user);

        return new ResponseRegisterUserDTO(this.repository.save(user).getId(), token);
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

    public ResponseProfileDTO profile(User user) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return new ResponseProfileDTO(user.getId(), user.getName(), user.getPhone(), user.getBirthDay().format(dateFormatter), user.getAddress());
    }

    public ResponseProfileDTO updateProfile(User user, RequestUpdateProfileDTO requestUpdateProfileDTO) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate birthdayDate = LocalDate.parse(requestUpdateProfileDTO.birthDay(), formatter);

        if (birthdayDate.isAfter(LocalDate.now().minusYears(18))) {
            throw new UserUnderAgeException();
        }

        user.setName(requestUpdateProfileDTO.name());
        user.setAddress(requestUpdateProfileDTO.address());
        user.setPhone(requestUpdateProfileDTO.phone());
        user.setBirthDay(birthdayDate);

        User saved = this.repository.save(user);
        return new ResponseProfileDTO(saved.getId(), saved.getName(), saved.getPhone(), saved.getBirthDay().format(formatter), saved.getAddress());
    }

    public void deleteProfile(User user) {
        this.repository.delete(user);
    }
}
