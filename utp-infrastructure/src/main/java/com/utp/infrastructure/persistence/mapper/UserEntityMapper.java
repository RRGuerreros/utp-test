package com.utp.infrastructure.persistence.mapper;

import com.utp.domain.entity.User;
import com.utp.infrastructure.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    User asUser(UserEntity userEntity);
}
