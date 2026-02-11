package com.employee_management.domain.absences.use_cases.impl;

import com.employee_management.domain.absences.dtos.AbsenceResponseDto;
import com.employee_management.domain.absences.dtos.ValiderAbsenceRequestDto;
import com.employee_management.domain.absences.ports.IAbsenceDataSourcePort;
import com.employee_management.domain.absences.use_cases.IValiderAbsenceUseCase;
import com.employee_management.domain.entities.Absence;
import com.employee_management.domain.entities.User;
import com.employee_management.domain.entities.enums.AbsenceStatus;
import com.employee_management.domain.exceptions.BusinessException;
import com.employee_management.domain.exceptions.ResourceNotFoundException;
import com.employee_management.domain.shared.mapping.AbsenceMapper;
import org.springframework.transaction.annotation.Transactional;

import com.employee_management.domain.users.ports.IUserDataSourcePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ValiderAbsenceUseCaseImpl implements IValiderAbsenceUseCase {

    private final IAbsenceDataSourcePort absenceDataSourcePort;
    private final IUserDataSourcePort userDataSourcePort;
    private final AbsenceMapper absenceMapper;

    @Override
    @Transactional
    public AbsenceResponseDto execute(ValiderAbsenceRequestDto validerAbsenceRequestDto) {
        Absence absence = absenceDataSourcePort.findById(validerAbsenceRequestDto.getAbsenceId())
                .orElseThrow(() -> new ResourceNotFoundException("Absence avec l'id " + validerAbsenceRequestDto.getAbsenceId() + " n'existe pas"));

        // Charger le Manager qui est connecté
        User manager = userDataSourcePort.findById(validerAbsenceRequestDto.getManagerId())
                .orElseThrow(() -> new ResourceNotFoundException("Manager introuvable (ID: " + validerAbsenceRequestDto.getManagerId() + ")"));

        // Charger l'employé qui a fait la demande
        User employe = absence.getEmployee();

        checkManagerAuthority(manager, employe);

        if (absence.getStatus() != AbsenceStatus.EN_COURS) {
            throw new BusinessException("L'absence a déjà été traitée. (Statut actuel : " + absence.getStatus() + ")");
        }
        // Traitement Validation ou Refus
        if (validerAbsenceRequestDto.isValide()) {
            validerAbsence(absence, employe);
        } else {
            refuserAbsence(absence);
        }

        // sauvegarde
        Absence savedAbsence = absenceDataSourcePort.save(absence);

        // retour DTO
        return absenceMapper.toDto(savedAbsence);
    }


    private void checkManagerAuthority(User manager, User employe) {
        if (manager.getDepartement() == null || employe.getDepartement() == null) {
            throw new RuntimeException("Impossible de valider : L'employé ou le manager n'est assigné à aucun département.");
        }

        if (!manager.getDepartement().getId().equals(employe.getDepartement().getId())) {
            throw new RuntimeException("ACCÈS REFUSÉ : Vous êtes du département "
                    + manager.getDepartement().getName()
                    + " et ne pouvez pas valider un employé du département "
                    + employe.getDepartement().getName());
        }
    }

    private void validerAbsence(Absence absence, User employe) {

        absence.valider();

        // mise à jour du solde
        int nouveauSolde = employe.getSoldeConges() - absence.getDuree();

        if (nouveauSolde < 0) {
            throw new RuntimeException("Impossible de valider : le solde est devenu insuffisant durant le processus.");
        }

        employe.setSoldeConges(nouveauSolde);

        // Sauvegarde de l'employé avec son nouveau solde
        userDataSourcePort.save(employe);
        log.info("Absence validée. Nouveau solde pour employé {}: {}", employe.getId(), nouveauSolde);
    }

    private void refuserAbsence(Absence absence) {
        absence.refuser();
        log.info("Absence refusée pour ID: {}", absence.getId());
    }


}