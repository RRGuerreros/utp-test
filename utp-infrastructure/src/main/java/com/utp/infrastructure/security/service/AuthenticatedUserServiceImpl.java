package com.utp.infrastructure.security.service;

import com.utp.domain.exception.EntityNotFoundException;
import com.utp.application.services.AuthenticatedUserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatedUserServiceImpl implements AuthenticatedUserService {
    @Override
    public String getAuthenticatedUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            throw new EntityNotFoundException("Usuario no autenticado");
        }
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        return userDetails.getUsername();
    }
}
