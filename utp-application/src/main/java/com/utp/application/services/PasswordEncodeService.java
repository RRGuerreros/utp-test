package com.utp.application.services;

public interface PasswordEncodeService {
    boolean matches(String rawPassword, String encodedPassword);
}
