package com.employee_management.domain.absences.use_cases;


import com.employee_management.domain.absences.dtos.AbsenceResponseDto;

import java.util.List;

public interface IGetAbsencesByEmployeeUseCase {

    /**
     * Récupère l'historique des absences d'un employé spécifique.
     * @param employeeId L'identifiant de l'employé.
     * @return Une liste d'absences (DTO).
     */
    List<AbsenceResponseDto> execute(Long employeeId);
}
