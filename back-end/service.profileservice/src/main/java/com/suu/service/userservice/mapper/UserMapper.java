package com.suu.service.userservice.mapper;

import com.suu.service.userservice.dto.User;
import com.suu.service.userservice.entity.UserDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDetails toEntity(User userDto);

    User toDto(UserDetails entity);

}
