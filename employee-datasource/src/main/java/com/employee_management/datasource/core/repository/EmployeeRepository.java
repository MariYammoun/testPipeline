package com.employee_management.datasource.core.repository;

import com.employee_management.datasource.core.entities.UserEntity;
import com.employee_management.domain.entities.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByEmail(String email);

    boolean existsByEmail(String email);
    Optional<UserEntity> findByUsername(String username);
}

