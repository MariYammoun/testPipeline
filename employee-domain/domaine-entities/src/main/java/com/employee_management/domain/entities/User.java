package com.employee_management.domain.entities;


import com.employee_management.domain.entities.enums.RoleType;
import lombok.*;


@Data
@NoArgsConstructor
public class User {
    private Long id;
    private String username;

    private String email;
    private String password;
    private RoleType role;
    private int soldeConges;
    private boolean active;
    private Departement departement;

    public User(Long id, String username, String email, String password, RoleType role, int soldeConges, Departement departement) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.active = true;
        this.soldeConges = soldeConges;
        this.departement = departement;

    }
}


    /*
    public void deactivate() { this.active = false; }
}
*/