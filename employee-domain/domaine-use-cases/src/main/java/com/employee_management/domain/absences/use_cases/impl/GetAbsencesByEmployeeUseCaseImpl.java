package com.employee_management.domain.absences.use_cases.impl;

import com.employee_management.domain.absences.dtos.AbsenceResponseDto;
import com.employee_management.domain.absences.ports.IAbsenceDataSourcePort;
import com.employee_management.domain.absences.use_cases.IGetAbsencesByEmployeeUseCase;
import com.employee_management.domain.entities.Absence;
import com.employee_management.domain.shared.mapping.AbsenceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetAbsencesByEmployeeUseCaseImpl implements IGetAbsencesByEmployeeUseCase {
    private final IAbsenceDataSourcePort absenceDataSourcePort;
    private final AbsenceMapper absenceMapper;



    @Override
    public List<AbsenceResponseDto> execute(Long employeeId) {
        List<Absence> absences = absenceDataSourcePort.findAllByEmployeeId(employeeId);
        return absenceMapper.toDtoList(absences);
    }

}
