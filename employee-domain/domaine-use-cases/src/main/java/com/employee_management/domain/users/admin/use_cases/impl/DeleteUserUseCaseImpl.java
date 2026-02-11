package com.employee_management.domain.users.admin.use_cases.impl;

import com.employee_management.domain.exceptions.ResourceNotFoundException;
import com.employee_management.domain.users.admin.use_cases.IDeleteUserUseCase;
import com.employee_management.domain.users.ports.IUserDataSourcePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteUserUseCaseImpl  implements IDeleteUserUseCase {
    private final IUserDataSourcePort userRepositoryPort;

    @Override
    public void execute(Long userId){
        log.info("Delete user {}",userId);

        if (userRepositoryPort.findById(userId).isEmpty()){
            throw new ResourceNotFoundException("impossible de supprimer : l'utilisateur introuvable. ");
        }
        userRepositoryPort.deleteById(userId);
    }
}
