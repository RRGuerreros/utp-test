package com.utp.application.usecase.auth;

import com.utp.application.annotation.UseCase;
import com.utp.application.dto.AuthRequest;
import com.utp.application.dto.AuthResponse;
import com.utp.application.services.PasswordEncodeService;
import com.utp.application.services.TokenGeneratorService;
import com.utp.application.services.UserService;
import com.utp.domain.entity.User;
import com.utp.domain.exception.BusinessException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class AuthUserCaseImpl implements AuthUserCase{

    private final PasswordEncodeService passwordEncodeService;
    private final TokenGeneratorService tokenGeneratorService;
    private final UserService userService;

    public AuthResponse execute(AuthRequest authRequest) {
        User user = userService.findByUsername(authRequest.getUsername());

        if (!passwordEncodeService.matches(authRequest.getPassword(), user.getPassword())) {
            throw new BusinessException("Credenciales inválidas");
        }

        return new AuthResponse(tokenGeneratorService.generateToken(user));
    }
}

