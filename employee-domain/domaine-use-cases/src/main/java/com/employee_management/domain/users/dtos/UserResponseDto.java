package com.employee_management.domain.users.dtos;

import com.employee_management.domain.entities.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder // <--- AJOUTEZ CETTE LIGNE
@AllArgsConstructor // <--- NÉCESSAIRE pour que le Builder fonctionne
@NoArgsConstructor
public class UserResponseDto {
    private Long id;
    private String username;
    private String email;
    private RoleType role;
    private String departementName;
    private int soldeConges;
    private boolean active;
}
