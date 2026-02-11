package com.employee_management.domain.users.admin.dtos;

import com.employee_management.domain.entities.enums.RoleType;
import lombok.Data;

@Data
public class CreateEmployeeRequestDto {
    private String username;
    private String email;
    private Long departementId;
    private int soldeConges;
    private RoleType role;

}
