package com.suu.service.profileservice.service;

import com.suu.service.profileservice.dto.AuthenticationRequest;
import com.suu.service.profileservice.dto.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User createUser(User user);

    String getToken(AuthenticationRequest authenticationRequest);
}
