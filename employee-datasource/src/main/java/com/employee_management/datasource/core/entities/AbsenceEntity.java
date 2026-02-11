package com.employee_management.datasource.core.entities;


import com.employee_management.domain.entities.enums.AbsenceStatus;
import com.employee_management.domain.entities.enums.AbsenceType;
import jakarta.persistence.*;
import lombok.Data;



@Entity
@Data
@Table(name = "absences")
public class AbsenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AbsenceType type;

    private int duree;

    private String justification;

    @Enumerated(EnumType.STRING)
    private AbsenceStatus status;

    @ManyToOne
    private UserEntity employee;
}

