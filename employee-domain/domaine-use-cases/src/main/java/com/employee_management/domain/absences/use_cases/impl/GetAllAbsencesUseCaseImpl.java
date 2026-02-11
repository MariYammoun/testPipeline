package com.employee_management.domain.absences.use_cases.impl;

import com.employee_management.domain.absences.dtos.AbsenceResponseDto;
import com.employee_management.domain.absences.ports.IAbsenceDataSourcePort;
import com.employee_management.domain.absences.use_cases.IGetAllAbsencesUseCase;
import com.employee_management.domain.entities.Absence;
import com.employee_management.domain.entities.User;
import com.employee_management.domain.entities.enums.RoleType;
import com.employee_management.domain.exceptions.ResourceNotFoundException;
import com.employee_management.domain.shared.mapping.AbsenceMapper;
import com.employee_management.domain.users.ports.IUserDataSourcePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor

public class GetAllAbsencesUseCaseImpl implements IGetAllAbsencesUseCase {
    private final IAbsenceDataSourcePort absenceDataSourcePort;
    private final IUserDataSourcePort userDataSourcePort;
    private final AbsenceMapper absenceMapper;


    public List<AbsenceResponseDto> execute(Long requesterId) {
        User requester = userDataSourcePort.findById(requesterId).orElseThrow(() -> new ResourceNotFoundException("utilisateur introuvable"));
        List<Absence> absences;
        if (requester.getRole() == RoleType.ADMIN) {
            absences = absenceDataSourcePort.findAll();

        } else if (requester.getRole() == RoleType.MANAGER) {
            if (requester.getDepartement() != null) {
                absences = absenceDataSourcePort.findAllByDepartementId(requester.getDepartement().getId());
            }

            else {
                // Si le manager n'a pas de département, il ne voit rien
                absences = Collections.emptyList();
            }
        }
        else {
            // si c est un Eemployee ---> Voit ses propres absences
            absences = absenceDataSourcePort.findAllByEmployeeId(requester.getId());
        }

        return absenceMapper.toDtoList(absences);

    }

}



