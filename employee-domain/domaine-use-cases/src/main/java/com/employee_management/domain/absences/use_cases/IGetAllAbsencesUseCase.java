package com.employee_management.domain.absences.use_cases;

import com.employee_management.domain.absences.dtos.AbsenceResponseDto;

import java.util.List;

public interface IGetAllAbsencesUseCase {

    List<AbsenceResponseDto> execute(Long requesterId);

}
