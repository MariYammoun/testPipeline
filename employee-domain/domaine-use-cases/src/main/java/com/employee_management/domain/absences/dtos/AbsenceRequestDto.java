package com.employee_management.domain.absences.dtos;

import com.employee_management.domain.entities.enums.AbsenceType;
import lombok.Data;
import jakarta.validation.constraints.NotNull;


@Data
public class AbsenceRequestDto {

    @NotNull(message = "L'employé est obligatoire")
    private Long employeeId;
    @NotNull(message = "Le type d'absence est obligatoire")
    private AbsenceType type;
    private int duree;
    private String justification;
}