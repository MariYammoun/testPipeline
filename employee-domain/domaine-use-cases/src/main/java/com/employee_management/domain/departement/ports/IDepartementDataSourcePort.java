package com.employee_management.domain.departement.ports;

import com.employee_management.domain.entities.Departement;

import java.util.Optional;


public interface IDepartementDataSourcePort {
    Optional<Departement> findById(Long id);
}
