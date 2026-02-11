package com.employee_management.domain.absences.ports;

import com.employee_management.domain.entities.Absence;

import java.util.List;
import java.util.Optional;


public interface IAbsenceDataSourcePort {
    Absence save(Absence absence);

    Optional<Absence> findById(Long id);

    List<Absence> findAll();

    List<Absence> findAllByEmployeeId(Long employeeId);
    List<Absence> findAllByDepartementId(Long departmentId);
}
