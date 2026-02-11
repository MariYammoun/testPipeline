package com.employee_management.domain.absences.dtos;

import com.employee_management.domain.entities.enums.AbsenceStatus;
import com.employee_management.domain.entities.enums.AbsenceType;
import lombok.Data;


@Data

public class AbsenceResponseDto {
    private Long id;
    private AbsenceType type;
    private int duree;
    private String justification;
    private AbsenceStatus status;
    private Long employeeId;


    public AbsenceType getType() {
        return this.type;
    }

}

