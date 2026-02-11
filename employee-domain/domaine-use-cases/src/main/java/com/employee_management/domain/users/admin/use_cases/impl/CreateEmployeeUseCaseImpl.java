package com.employee_management.domain.users.admin.use_cases.impl;

import com.employee_management.domain.shared.ports.IEmailServiceDataSourcePort;
import com.employee_management.domain.authentification.ports.IPasswordEncoderDataSourcePort;
import com.employee_management.domain.departement.ports.IDepartementDataSourcePort;
import com.employee_management.domain.users.admin.dtos.CreateEmployeeRequestDto;
import com.employee_management.domain.users.admin.use_cases.ICreateEmployeeUseCase;
import com.employee_management.domain.entities.Departement;
import com.employee_management.domain.entities.User;
import com.employee_management.domain.entities.enums.RoleType;
import com.employee_management.domain.exceptions.BusinessException;
import com.employee_management.domain.exceptions.ResourceNotFoundException;
import com.employee_management.domain.users.ports.IUserDataSourcePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class CreateEmployeeUseCaseImpl implements ICreateEmployeeUseCase {

    private final IUserDataSourcePort userRepositoryPort;
    private final IDepartementDataSourcePort departementPort;
    private final IPasswordEncoderDataSourcePort passwordEncoder;
    private final IEmailServiceDataSourcePort emailService;


    @Override
    public User execute(CreateEmployeeRequestDto request) {
        // verifier existant
        if (userRepositoryPort.existsByEmail(request.getEmail())) {
            throw new BusinessException("Email déjà utilisé");
        }

        // genération modp generique
        String rawPassword = UUID.randomUUID().toString().substring(0, 8);

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        if (request.getRole() == null) {
            user.setRole(RoleType.EMPLOYEE);

        }
        else {
            user.setRole(request.getRole());
        }
        user.setSoldeConges(request.getSoldeConges());
        user.setActive(true);

        // departement
        Departement dept = departementPort.findById(request.getDepartementId())
                .orElseThrow(() -> new ResourceNotFoundException("Département inconnu"));
        user.setDepartement(dept);

        // hashage et sauvegarde
        user.setPassword(passwordEncoder.encode(rawPassword));
        userRepositoryPort.save(user);

        // envoi des identif
        emailService.sendCredentials(user.getEmail(), rawPassword);

        return user;
    }
}