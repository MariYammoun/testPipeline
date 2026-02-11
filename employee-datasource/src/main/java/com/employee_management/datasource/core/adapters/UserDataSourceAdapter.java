package com.employee_management.datasource.core.adapters;

import com.employee_management.datasource.core.entities.UserEntity;
import com.employee_management.datasource.core.mappers.EmployeeDataSourceMapper;
import com.employee_management.datasource.core.repository.EmployeeRepository;

import com.employee_management.domain.entities.User;
import com.employee_management.domain.users.ports.IUserDataSourcePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserDataSourceAdapter implements IUserDataSourcePort {
    private final EmployeeRepository employeeRepository;
    private final EmployeeDataSourceMapper mapper;

    @Override
    public Optional<User> findById(Long id) {
        Optional<UserEntity> entityOptional = employeeRepository.findById(id);
        return entityOptional.map(mapper::toDomain);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        Optional<UserEntity> entityOptional = employeeRepository.findByUsername(username);
        return entityOptional.map(mapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<UserEntity> entityOptional = employeeRepository.findByEmail(email);
        return entityOptional.map(mapper::toDomain);
    }

    @Override
    public User save(User userDomain) {
        log.debug("Sauvegarde de l'utilisateur ID: {}", userDomain.getId());

        UserEntity entityToSave = mapper.toEntity(userDomain);
        UserEntity savedEntity = employeeRepository.save(entityToSave);
        return mapper.toDomain(savedEntity);
    }
    @Override
    public boolean existsByEmail(String email) {

        return employeeRepository.existsByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return employeeRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }
}


