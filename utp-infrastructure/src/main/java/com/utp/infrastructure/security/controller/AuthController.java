package com.utp.infrastructure.security.controller;

import com.utp.application.dto.AuthResponse;
import com.utp.infrastructure.security.dto.AuthRequest;
import com.utp.infrastructure.security.facade.AuthFacade;
import com.utp.infrastructure.security.mapper.AuthRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthFacade authFacade;
    private final AuthRestMapper authRestMapper;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest authRequest){
        return ResponseEntity.ok(authFacade.authenticate(
                authRestMapper.asAuthCommand(authRequest)
        ));
    }
}
