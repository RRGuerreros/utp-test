package com.utp.application.usecase.auth;

import com.utp.application.dto.AuthRequest;
import com.utp.application.dto.AuthResponse;

public interface AuthUserCase {
    AuthResponse execute(AuthRequest authRequest);
}
