package com.suu.service.profileservice.controller;

import com.suu.service.profileservice.dto.AuthenticationRequest;
import com.suu.service.profileservice.dto.AuthenticationResponse;
import com.suu.service.profileservice.dto.User;
import com.suu.service.profileservice.service.AuthenticationService;
import com.suu.service.profileservice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/authentication")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    private AuthenticationResponse login(@RequestBody @Valid AuthenticationRequest  authenticationRequest) {
        return authenticationService.login(authenticationRequest);
    }
    @PostMapping("/signup")
    public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
        return new ResponseEntity<>(authenticationService.signUp(user), HttpStatus.CREATED);
    }
}
