package com.employee_management.domain.absences.dtos;

import lombok.Data;

@Data
public class ValiderAbsenceRequestDto {
    private Long absenceId;
    private Long managerId;
    private boolean valide;
}
