package com.utp.infrastructure.service;

import com.utp.domain.exception.EntityNotFoundException;
import com.utp.application.services.AuthenticatedUserService;
import com.utp.application.services.UserService;
import com.utp.domain.entity.User;
import com.utp.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticatedUserService authenticatedUserService;

    @Override
    public User getUserAuthenticated(){
        String username = authenticatedUserService.getAuthenticatedUsername();
        return findByUsername(username);
    }

    @Override
    public User findByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
    }
}
