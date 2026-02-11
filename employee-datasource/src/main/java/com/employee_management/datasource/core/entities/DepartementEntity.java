package com.employee_management.datasource.core.entities;



import com.employee_management.domain.entities.Departement;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "departements")
public class DepartementEntity extends Departement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}
