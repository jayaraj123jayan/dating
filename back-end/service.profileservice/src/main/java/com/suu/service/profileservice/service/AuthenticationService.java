package com.suu.service.profileservice.service;

import com.suu.service.profileservice.dto.AuthenticationRequest;
import com.suu.service.profileservice.dto.AuthenticationResponse;
import com.suu.service.profileservice.dto.User;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    AuthenticationResponse login(AuthenticationRequest authenticationRequest);
    User signUp(User user);
}
