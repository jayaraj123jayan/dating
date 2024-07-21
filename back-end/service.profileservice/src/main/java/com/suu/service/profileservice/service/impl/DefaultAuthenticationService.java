package com.suu.service.profileservice.service.impl;

import com.suu.service.profileservice.dto.AuthenticationRequest;
import com.suu.service.profileservice.dto.AuthenticationResponse;
import com.suu.service.profileservice.dto.User;
import com.suu.service.profileservice.exception.NotFoundException;
import com.suu.service.profileservice.exception.UnauthorizedException;
import com.suu.service.profileservice.exception.UserAlreadyExistException;
import com.suu.service.profileservice.service.AuthenticationService;
import com.suu.service.profileservice.service.AuthenticationTokenService;
import com.suu.service.profileservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DefaultAuthenticationService implements AuthenticationService {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationTokenService authenticationTokenService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        try {
            final User user = userService.getUserByEmail(authenticationRequest.getEmail());
            return AuthenticationResponse.builder()
                    .user(user)
                    .token(generateToken(user))
                    .build();
        } catch (NotFoundException e) {
            throw new UnauthorizedException(e.getMessage());
        }

    }

    @Override
    public User signUp(User user) {
        try {
            userService.getUserByEmail(user.getEmail());
            throw new UserAlreadyExistException("User already exist.");
        } catch (NotFoundException e) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userService.createUser(user);
        }
    }

    private String generateToken(User user) {
        return authenticationTokenService.generateToken(user);
    }
}
