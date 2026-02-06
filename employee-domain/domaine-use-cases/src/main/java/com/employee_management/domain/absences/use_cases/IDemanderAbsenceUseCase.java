package com.employee_management.domain.absences.use_cases;

import com.employee_management.domain.absences.dtos.AbsenceRequestDto;
import com.employee_management.domain.absences.dtos.AbsenceResponseDto;


public interface IDemanderAbsenceUseCase {
    AbsenceResponseDto execute(AbsenceRequestDto dto);
}
