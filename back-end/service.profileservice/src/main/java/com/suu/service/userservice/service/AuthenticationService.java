package com.suu.service.userservice.service;

import com.suu.service.userservice.dto.AuthenticationRequest;
import com.suu.service.userservice.dto.AuthenticationResponse;
import com.suu.service.userservice.dto.User;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    AuthenticationResponse login(AuthenticationRequest authenticationRequest);
    User signUp(User user);
}
