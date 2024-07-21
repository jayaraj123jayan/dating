package com.suu.service.profileservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponse {
    private User user;
    private String token;
}
