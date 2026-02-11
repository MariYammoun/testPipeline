package com.employee_management.domain.absences.use_cases.impl;

import com.employee_management.domain.absences.ports.IAbsenceDataSourcePort;
import com.employee_management.domain.absences.dtos.AbsenceRequestDto;
import com.employee_management.domain.absences.dtos.AbsenceResponseDto;
import com.employee_management.domain.absences.use_cases.IDemanderAbsenceUseCase;
import com.employee_management.domain.entities.Absence;
import com.employee_management.domain.entities.User;
import com.employee_management.domain.exceptions.BusinessException;
import com.employee_management.domain.exceptions.ResourceNotFoundException;
import com.employee_management.domain.shared.mapping.AbsenceMapper;
import com.employee_management.domain.users.ports.IUserDataSourcePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@RequiredArgsConstructor
public class DemanderAbsenceUseCaseImpl  implements IDemanderAbsenceUseCase {

    private final IAbsenceDataSourcePort absenceDataSourcePort;
    private final IUserDataSourcePort userDataSourcePort;
    private final AbsenceMapper absenceMapper;

    public AbsenceResponseDto execute(AbsenceRequestDto dto) {
        // On vérifie que l'employé existe avant la demande
        User employee = userDataSourcePort.findById(dto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur introuvable avec l'ID : " + dto.getEmployeeId()));
        log.info("Utilisateur trouvé : {}", employee.getSoldeConges());

        // vérifier le solde congé
        if (employee.getSoldeConges() < dto.getDuree()) {
            throw new BusinessException("Solde insuffisant. Vous avez "
                    + employee.getSoldeConges() + " jours, mais vous demandez " + dto.getDuree());
        }

        Absence absence = new Absence(
                null,
                dto.getType(),
                dto.getDuree(),
                dto.getJustification(),
                employee
        );

        Absence saved = absenceDataSourcePort.save(absence);

        return absenceMapper.toDto(saved);
    }


}


