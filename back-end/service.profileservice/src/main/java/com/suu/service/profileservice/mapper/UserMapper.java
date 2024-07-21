package com.suu.service.profileservice.mapper;

import com.suu.service.profileservice.dto.User;
import com.suu.service.profileservice.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserProfile toEntity(User userDto);

    User toDto(UserProfile entity);

}
