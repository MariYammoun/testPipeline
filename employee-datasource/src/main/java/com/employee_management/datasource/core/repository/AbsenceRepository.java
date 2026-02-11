package com.employee_management.datasource.core.repository;

import com.employee_management.datasource.core.entities.AbsenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbsenceRepository extends JpaRepository<AbsenceEntity,Long> {
    List<AbsenceEntity> findAllByEmployeeId(Long employeeId);
    List<AbsenceEntity> findAllByEmployeeDepartementId(Long departementId);
}
