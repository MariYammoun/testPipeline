package com.employee_management.domain.authentification.ports;

import com.employee_management.domain.entities.User;

/**
 * @author $(USER)
 **/
public interface ITokenProviderDataSourcePort {
    // génèrer le token JWT
    String generateToken(User user);
}
