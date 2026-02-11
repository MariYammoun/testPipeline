package com.employee_management.datasource.core.adapters.security;

import com.employee_management.domain.authentification.ports.IPasswordEncoderDataSourcePort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderDataSourceAdapter implements IPasswordEncoderDataSourcePort {

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();



    public String encode(String rawPassword)
    {
        return encoder.encode(rawPassword);
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }
}