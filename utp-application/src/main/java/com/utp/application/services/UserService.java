package com.utp.application.services;

import com.utp.domain.entity.User;

public interface UserService {
    User getUserAuthenticated();
    User findByUsername(String username);
}
