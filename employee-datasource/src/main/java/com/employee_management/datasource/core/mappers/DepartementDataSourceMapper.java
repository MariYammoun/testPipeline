package com.employee_management.datasource.core.mappers;

import com.employee_management.datasource.core.entities.DepartementEntity;
import com.employee_management.domain.entities.Departement;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface DepartementDataSourceMapper {

    Departement toDomain(DepartementEntity entity);

    DepartementEntity toEntity(Departement domain);
}