package com.suu.service.profileservice.mapper;

import com.suu.service.profileservice.dto.User;
import com.suu.service.profileservice.entity.UserProfile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserProfile toEntity(User userDto);
    User toDto(UserProfile entity);
}
