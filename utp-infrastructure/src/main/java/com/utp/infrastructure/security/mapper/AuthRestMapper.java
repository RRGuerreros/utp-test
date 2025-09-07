package com.utp.infrastructure.security.mapper;

import com.utp.application.dto.AuthRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthRestMapper {
    AuthRequest asAuthCommand(com.utp.infrastructure.security.dto.AuthRequest authRequest);
}
