package com.employee_management.employee_api.resources;

import com.employee_management.domain.authentification.dtos.ChangePasswordRequestDto;
import com.employee_management.domain.authentification.use_cases.IChangePasswordUseCase;


import com.employee_management.domain.entities.User;
import com.employee_management.domain.exceptions.ResourceNotFoundException;
import com.employee_management.domain.users.employee.use_cases.IConsulterSoldeUseCase;
import com.employee_management.domain.users.ports.IUserDataSourcePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author $(USER)
 **/
@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final IChangePasswordUseCase changePasswordUseCase;
    private final IUserDataSourcePort userDataSourcePort;
    private final IConsulterSoldeUseCase consulterSoldeUseCase;


    @PostMapping("/password")
    public ResponseEntity<String> changePassword(
            @RequestBody ChangePasswordRequestDto dto,
            Authentication auth
    ) {
        String email = auth.getName();
        User user = userDataSourcePort.findByEmail(email).orElseThrow();

        changePasswordUseCase.execute(user.getId(), dto);

        return ResponseEntity.ok("Mot de passe changé !");
    }

    @GetMapping("/solde")
    public ResponseEntity<Map<String, Integer>> getMonSolde(Authentication auth) {
        String email = auth.getName();
        User user = userDataSourcePort.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User introuvable"));
        int solde = consulterSoldeUseCase.execute(user.getId());
        return ResponseEntity.ok(Map.of("solde", solde));
    }
}