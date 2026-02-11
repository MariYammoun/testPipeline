package com.employee_management.domain.entities;


import com.employee_management.domain.entities.enums.AbsenceStatus;
import com.employee_management.domain.entities.enums.AbsenceType;
import lombok.*;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Absence {
    @EqualsAndHashCode.Include

    private Long id;
    private AbsenceType type;
    private int duree;
    private String justification;
    private AbsenceStatus status;
    private User employee;



    public Absence(Long id, AbsenceType type, int duree, String justification, User employee) {
        this.id = id;
        this.type = type;
        this.duree = duree;
        this.justification = justification;
        this.employee = employee;
        this.status = AbsenceStatus.EN_COURS; // par défaut en attendant la validation ou le refus de manager
    }


    public void valider() {
        this.status = AbsenceStatus.VALIDE;
    }

    public void refuser() {
        this.status = AbsenceStatus.REFUSE;
    }



}
