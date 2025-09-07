package com.utp.domain.repository;

import com.utp.domain.entity.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByUsername(String username);
}
