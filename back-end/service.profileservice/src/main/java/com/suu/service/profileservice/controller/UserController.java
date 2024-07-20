package com.suu.service.profileservice.controller;

import com.suu.service.profileservice.dto.AuthenticationRequest;
import com.suu.service.profileservice.dto.User;
import com.suu.service.profileservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/profile")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/get-token")
    public ResponseEntity<String> getToken(@RequestBody AuthenticationRequest authenticationRequest) {
        return new ResponseEntity<>(userService.getToken(authenticationRequest), HttpStatus.OK);
    }

}
