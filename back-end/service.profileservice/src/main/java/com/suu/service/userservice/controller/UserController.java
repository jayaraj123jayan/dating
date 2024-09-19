package com.suu.service.userservice.controller;

import com.suu.service.userservice.dto.AuthenticationRequest;
import com.suu.service.userservice.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("v1/user")
@SecurityRequirement(name="Bearer Authentication")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/get-token")
    public ResponseEntity<String> getToken(@RequestBody AuthenticationRequest authenticationRequest) {
        return new ResponseEntity<>(userService.getToken(authenticationRequest), HttpStatus.OK);
    }

}
