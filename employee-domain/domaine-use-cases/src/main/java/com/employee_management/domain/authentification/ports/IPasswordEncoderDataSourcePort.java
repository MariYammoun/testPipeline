package com.employee_management.domain.authentification.ports;

/**
 * @author $(USER)
 **/
public interface IPasswordEncoderDataSourcePort {
    // verifie si le mot de passe  (saisi) correspond au hash
    boolean matches(String rawPassword, String encodedPassword);
    String encode(String rawPassword);
}
