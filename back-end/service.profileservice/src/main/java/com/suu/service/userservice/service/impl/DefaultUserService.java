package com.suu.service.userservice.service.impl;

import com.suu.service.userservice.dto.AuthenticationRequest;
import com.suu.service.userservice.dto.User;
import com.suu.service.userservice.exception.NotFoundException;
import com.suu.service.userservice.mapper.UserMapper;
import com.suu.service.userservice.repository.UserRepository;
import com.suu.service.userservice.service.UserService;
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

    @Override
    public User getUserByEmail(String email) {
        final var user = userRepository.findByEmail(email)
                .orElseThrow(()->new NotFoundException("User not fount"));
        return userMapper.toDto(userRepository.save(user));
    }
}
