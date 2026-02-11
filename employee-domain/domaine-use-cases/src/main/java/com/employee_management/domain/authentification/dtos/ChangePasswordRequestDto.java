package com.employee_management.domain.authentification.dtos;

import lombok.Data;

@Data
public class ChangePasswordRequestDto {
    private String currentPassword;
    private String newPassword;
    //ajouter le champ confirmation de mdp
}