package com.employee_management.domain.authentification.use_cases.impl;


import com.employee_management.domain.authentification.dtos.AuthResponseDto;
import com.employee_management.domain.authentification.dtos.LoginRequestDto;
import com.employee_management.domain.authentification.ports.IPasswordEncoderDataSourcePort;
import com.employee_management.domain.authentification.ports.ITokenProviderDataSourcePort;
import com.employee_management.domain.authentification.use_cases.ILoginUseCase;
import com.employee_management.domain.entities.User;
import com.employee_management.domain.users.ports.IUserDataSourcePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginUseCaseImpl implements ILoginUseCase {

    private final IUserDataSourcePort userRepositoryPort;
    private final IPasswordEncoderDataSourcePort passwordEncoder;
    private final ITokenProviderDataSourcePort tokenProvider;

    @Override
    public AuthResponseDto login(LoginRequestDto request) {
        log.info("Tentative de connexion pour : {}", request.getEmail());

        //chercher l'utilisateur
        User user = userRepositoryPort.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Email ou mot de passe incorrect"));

        // verifier le mdp
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            log.warn("Échec connexion  pour : {}", request.getEmail());
            throw new RuntimeException("Email ou mot de passe incorrect");
        }

        // générer le Token
        String token = tokenProvider.generateToken(user);

        // retourner la réponse
        return AuthResponseDto.builder()
                .accessToken(token)
                .tokenType("Bearer")
                .userId(user.getId())
                .role(user.getRole().name())
                .build();
    }
}
