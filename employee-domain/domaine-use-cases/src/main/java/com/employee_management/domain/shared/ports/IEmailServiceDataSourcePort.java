package com.employee_management.domain.shared.ports;

/**
 * @author $(USER)
 **/
public interface IEmailServiceDataSourcePort {
    void sendCredentials(String email, String password);
}
