package com.employee_management.domain.authentification.use_cases.impl;

import com.employee_management.domain.authentification.dtos.ChangePasswordRequestDto;
import com.employee_management.domain.authentification.ports.IPasswordEncoderDataSourcePort;
import com.employee_management.domain.authentification.use_cases.IChangePasswordUseCase;
import com.employee_management.domain.entities.User;
import com.employee_management.domain.exceptions.BusinessException;
import com.employee_management.domain.exceptions.ResourceNotFoundException;

import com.employee_management.domain.users.ports.IUserDataSourcePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class ChangePasswordUseCaseImpl implements IChangePasswordUseCase {

    private final IUserDataSourcePort userRepositoryPort;
    private final IPasswordEncoderDataSourcePort passwordEncoder;

    @Override

    public void execute(Long userId, ChangePasswordRequestDto request) {
        log.info("Changement de mot de passe demandé pour User ID : {}", userId);

        // recupérer l'utilisateur
        User user = userRepositoryPort.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur introuvable"));

        // vrifier que ancien mdp est correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            log.warn("Échec changement mot de passe : ancien mot de passe incorrect");
            throw new BusinessException("Le mot de passe actuel est incorrect");
        }

        // encoder le nouveau mdp
        String encodedNewPassword = passwordEncoder.encode(request.getNewPassword());
        user.setPassword(encodedNewPassword);

        // sauvegarder
        userRepositoryPort.save(user);

        log.info("Mot de passe mis à jour avec succès.");
    }
}
