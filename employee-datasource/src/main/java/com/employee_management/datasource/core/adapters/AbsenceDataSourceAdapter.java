package com.employee_management.datasource.core.adapters;

import com.employee_management.datasource.core.entities.AbsenceEntity;
import com.employee_management.datasource.core.mappers.AbsenceDataSourceMapper;
import com.employee_management.datasource.core.repository.AbsenceRepository;

import com.employee_management.domain.absences.ports.IAbsenceDataSourcePort;
import com.employee_management.domain.entities.Absence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AbsenceDataSourceAdapter implements IAbsenceDataSourcePort {

    private final AbsenceRepository absenceRepository;
    private final AbsenceDataSourceMapper absenceMapper;

    @Override
    public Absence save(Absence absence) {
        // domaine -> entité jpa
        AbsenceEntity entity = absenceMapper.toEntity(absence);
        AbsenceEntity savedEntity = absenceRepository.save(entity);

        // entité jpa -> domaine
        return absenceMapper.toDomain(savedEntity);
    }
    @Override
    public Optional<Absence> findById(Long id) {
        return absenceRepository.findById(id)
                .map(absenceMapper::toDomain);
    }

    @Override
    public List<Absence> findAll() {
        return absenceRepository.findAll().stream()
                .map(absenceMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Absence> findAllByDepartementId(Long departementId) {
        return absenceRepository.findAllByEmployeeDepartementId(departementId).stream()
                .map(absenceMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Absence> findAllByEmployeeId(Long employeeId) {
        return absenceRepository.findAllByEmployeeId(employeeId).stream()
                .map(absenceMapper::toDomain)
                .collect(Collectors.toList());
    }
}

