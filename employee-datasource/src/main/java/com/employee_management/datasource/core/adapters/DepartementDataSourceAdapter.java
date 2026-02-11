package com.employee_management.datasource.core.adapters;

import com.employee_management.datasource.core.mappers.DepartementDataSourceMapper;
import com.employee_management.datasource.core.repository.DepartementRepository;
import com.employee_management.domain.departement.ports.IDepartementDataSourcePort;
import com.employee_management.domain.entities.Departement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class DepartementDataSourceAdapter implements IDepartementDataSourcePort {

    private final DepartementRepository repository;
    private final DepartementDataSourceMapper mapper;

    @Override
    public Optional<Departement> findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDomain);

    }
}