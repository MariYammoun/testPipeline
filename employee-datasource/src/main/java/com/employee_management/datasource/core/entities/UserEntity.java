package com.employee_management.datasource.core.entities;


import com.employee_management.domain.entities.enums.RoleType;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    private boolean active;
    @Column(name = "solde_conges", nullable = true)
    private int soldeConges = 25;

    @ManyToOne
    @JoinColumn(name = "departement_id")
    private DepartementEntity departement;


}

