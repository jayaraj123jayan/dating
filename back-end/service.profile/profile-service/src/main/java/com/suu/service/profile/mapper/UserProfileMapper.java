package com.suu.service.profile.mapper;

import com.suu.service.profile.dao.UserProfileEntity;
import com.suu.service.profile.dto.UserProfile;
import org.mapstruct.Mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    UserProfile toDto(UserProfileEntity entity);

    UserProfileEntity toEntity(UserProfile dto);

    default List<UserProfile> toDtos(List<UserProfileEntity> userProfileEntities) {
        return userProfileEntities.stream().map(this::toDto).collect(Collectors.toList());
    };
}
