package com.employee_management.employee_api.resources;


import com.employee_management.domain.authentification.dtos.AuthResponseDto;
import com.employee_management.domain.authentification.dtos.LoginRequestDto;
import com.employee_management.domain.authentification.use_cases.ILoginUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final ILoginUseCase loginUseCase;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginRequestDto request) {

        AuthResponseDto response = loginUseCase.login(request);
        return ResponseEntity.ok(response);
    }
}