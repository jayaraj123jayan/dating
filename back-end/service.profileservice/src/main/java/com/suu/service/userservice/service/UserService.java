package com.suu.service.userservice.service;

import com.suu.service.userservice.dto.AuthenticationRequest;
import com.suu.service.userservice.dto.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User createUser(User user);

    String getToken(AuthenticationRequest authenticationRequest);

    User getUserByEmail(String email);
}
