package com.employee_management.domain.users.admin.use_cases.impl;

import com.employee_management.domain.entities.User;
import com.employee_management.domain.entities.enums.RoleType;
import com.employee_management.domain.exceptions.ResourceNotFoundException;
import com.employee_management.domain.users.admin.use_cases.IUpdateUserRoleUseCase;
import com.employee_management.domain.users.ports.IUserDataSourcePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateUserRoleUseCaseImpl implements IUpdateUserRoleUseCase {
    private final IUserDataSourcePort userRepositoryPort;

    @Override
    public void execute(Long userId, RoleType newRole) {
        log.info("mise à jour du role pour l user ID : {} -> nouveau role:{}", userId, newRole);

        User user = userRepositoryPort.findById(userId).orElseThrow(() -> new ResourceNotFoundException("utilisateur introuvable"));

        user.setRole(newRole);
        userRepositoryPort.save(user);
    }

}
