package com.employee_management.datasource.core.adapters;



import com.employee_management.domain.shared.ports.IEmailServiceDataSourcePort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailServiceDataSourceAdapter implements IEmailServiceDataSourcePort {
    @Override
    public void sendCredentials(String email, String password) {
        log.info("EMAIL ENVOYÉ À : " + email);
        log.info("MOT DE PASSE GÉNÉRÉ : " + password);
    }
}
