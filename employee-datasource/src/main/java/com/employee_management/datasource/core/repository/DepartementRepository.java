package com.employee_management.datasource.core.repository;

import com.employee_management.datasource.core.entities.DepartementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author $(USER)
 **/
public interface DepartementRepository extends JpaRepository<DepartementEntity, Long> {
}
