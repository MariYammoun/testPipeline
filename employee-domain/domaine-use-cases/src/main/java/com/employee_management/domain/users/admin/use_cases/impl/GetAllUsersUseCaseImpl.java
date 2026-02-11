package com.employee_management.domain.users.admin.use_cases.impl;

import com.employee_management.domain.users.admin.use_cases.IGetAllUsersUseCase;
import com.employee_management.domain.users.dtos.UserResponseDto;
import com.employee_management.domain.users.ports.IUserDataSourcePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

@RequiredArgsConstructor
public class GetAllUsersUseCaseImpl implements IGetAllUsersUseCase {

    private final IUserDataSourcePort userRepositoryPort;

    @Override
    public List<UserResponseDto> execute() {
        return userRepositoryPort.findAll().stream()
                .map(user -> UserResponseDto.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .role(user.getRole())
                        .soldeConges(user.getSoldeConges())
                        .active(user.isActive())
                        .departementName(user.getDepartement() != null ? user.getDepartement().getName() : "Aucun")
                        .build())
                .collect(Collectors.toList());
    }

}
