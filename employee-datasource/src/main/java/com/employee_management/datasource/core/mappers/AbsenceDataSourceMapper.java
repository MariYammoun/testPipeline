package com.employee_management.datasource.core.mappers;

import com.employee_management.datasource.core.entities.AbsenceEntity;
import com.employee_management.domain.entities.Absence;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {EmployeeDataSourceMapper.class})
public interface AbsenceDataSourceMapper {

    Absence toDomain(AbsenceEntity entity);
    AbsenceEntity toEntity(Absence domain);
}
