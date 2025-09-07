package com.utp.application.services;

import com.utp.domain.entity.User;

public interface TokenGeneratorService {
    String generateToken(User user);
}
