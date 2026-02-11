package com.employee_management.domain.users.employee.use_cases.impl;

import com.employee_management.domain.users.employee.use_cases.IConsulterSoldeUseCase;
import com.employee_management.domain.entities.User;
import com.employee_management.domain.exceptions.ResourceNotFoundException;
import com.employee_management.domain.users.ports.IUserDataSourcePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ConsulterSoldeUseCaseImpl implements IConsulterSoldeUseCase {
    private final IUserDataSourcePort userRepositoryPort;


    @Override
    public int execute(Long userId) {
        return userRepositoryPort.findById(userId).map(User::getSoldeConges).orElseThrow(() -> new ResourceNotFoundException("user introuvable"));
    }
}
