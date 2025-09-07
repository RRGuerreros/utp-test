package com.utp.infrastructure.security.facade;

import com.utp.application.dto.AuthRequest;
import com.utp.application.dto.AuthResponse;
import com.utp.application.usecase.auth.AuthUserCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthFacade {

    private final AuthUserCase authUserCase;

    @Transactional
    public AuthResponse authenticate(AuthRequest authRequest){
        return authUserCase.execute(authRequest);
    }
}
