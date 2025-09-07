package com.utp.infrastructure.repository;

import com.utp.domain.entity.User;
import com.utp.domain.repository.UserRepository;
import com.utp.infrastructure.persistence.mapper.UserEntityMapper;
import com.utp.infrastructure.persistence.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class UserRepositoryJpaAdapter implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final UserEntityMapper userEntityMapper;

    @Override
    public Optional<User> findByUsername(String username) {
        return userJpaRepository.findByUsername(username).map(userEntityMapper::asUser);
    }
}
