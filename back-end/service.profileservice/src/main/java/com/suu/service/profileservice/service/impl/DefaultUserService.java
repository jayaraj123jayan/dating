package com.suu.service.profileservice.service.impl;

import com.suu.service.profileservice.dto.AuthenticationRequest;
import com.suu.service.profileservice.dto.User;
import com.suu.service.profileservice.mapper.UserMapper;
import com.suu.service.profileservice.repository.UserRepository;
import com.suu.service.profileservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DefaultUserService implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;
    @Override
    public User createUser(User user) {
        log.info("Creating user. user={}", user);
        final var userEntity = userMapper.toEntity(user);
        return userMapper.toDto(userRepository.save(userEntity));
    }

    @Override
    public String getToken(AuthenticationRequest authenticationRequest) {
        final var user = userRepository.findByEmail(authenticationRequest.getEmail());
        return null;
    }
}
