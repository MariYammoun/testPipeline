package com.employee_management.employee_api.resources;


import com.employee_management.domain.absences.dtos.AbsenceRequestDto;
import com.employee_management.domain.absences.dtos.AbsenceResponseDto;
import com.employee_management.domain.absences.dtos.ValiderAbsenceRequestDto;
import com.employee_management.domain.absences.use_cases.IDemanderAbsenceUseCase;
import com.employee_management.domain.absences.use_cases.IGetAbsencesByEmployeeUseCase;
import com.employee_management.domain.absences.use_cases.IGetAllAbsencesUseCase;
import com.employee_management.domain.absences.use_cases.IValiderAbsenceUseCase;

import com.employee_management.domain.entities.User;
import com.employee_management.domain.exceptions.ResourceNotFoundException;
import com.employee_management.domain.users.ports.IUserDataSourcePort;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/absences")
public class AbsenceController {

    private final IDemanderAbsenceUseCase demanderAbsenceUseCase;
    private final IGetAbsencesByEmployeeUseCase getAbsencesByEmployeeUseCase;
    private final IValiderAbsenceUseCase validerAbsenceUseCase;
    private final IGetAllAbsencesUseCase getAllAbsencesUseCase;
    private final IUserDataSourcePort userDataSourcePort;


    @PostMapping
    public ResponseEntity<AbsenceResponseDto> demanderAbsence(@Valid @RequestBody AbsenceRequestDto requestDto) {
        log.info("Requête reçue pour demander une absence. Employé ID: {}", requestDto.getEmployeeId());

        AbsenceResponseDto response = demanderAbsenceUseCase.execute(requestDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PutMapping("/{absenceId}/valider")
    public ResponseEntity<AbsenceResponseDto> validerAbsence(
            @PathVariable Long absenceId,
            @RequestBody ValiderAbsenceRequestDto partialDto,
            Authentication authentication
    ) {

        String email = authentication.getName();
        User manager = userDataSourcePort.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("manager introuvable"));

        partialDto.setAbsenceId(absenceId);
        partialDto.setManagerId(manager.getId());
        log.info("Validation Absence ID: {} par Manager : {} (ID: {})",
                absenceId, email, manager.getId());

        AbsenceResponseDto response = validerAbsenceUseCase.execute(partialDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<AbsenceResponseDto>> getAbsencesByEmployee(@PathVariable Long employeeId) {
        List<AbsenceResponseDto> absences = getAbsencesByEmployeeUseCase.execute(employeeId);
        return ResponseEntity.ok(absences);
    }

    @GetMapping
    public ResponseEntity<List<AbsenceResponseDto>> getAllAbsences(Authentication authentication) {
        // recuperer l'email depuis le contexte de sécurité (le Token)
        String email = authentication.getName();
        log.info("Demande de récupération des absences par : {}", email);

        // recuperer les infos de user
        User user = userDataSourcePort.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur connecté introuvable en base de données."));

        // appeler usecase avec l' id de 'user connecté
        List<AbsenceResponseDto> absences = getAllAbsencesUseCase.execute(user.getId());

        return ResponseEntity.ok(absences);
    }
    }


