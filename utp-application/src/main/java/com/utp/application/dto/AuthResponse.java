package com.utp.application.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public class AuthResponse  implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String token;
}
