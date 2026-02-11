package com.employee_management.domain.absences.use_cases;

import com.employee_management.domain.absences.dtos.AbsenceResponseDto;
import com.employee_management.domain.absences.dtos.ValiderAbsenceRequestDto;



public interface IValiderAbsenceUseCase {
    AbsenceResponseDto execute(ValiderAbsenceRequestDto validerAbsenceRequestDto);
}
