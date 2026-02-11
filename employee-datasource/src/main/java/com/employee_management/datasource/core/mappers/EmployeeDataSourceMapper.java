package com.employee_management.datasource.core.mappers;

import com.employee_management.datasource.core.entities.UserEntity;
import com.employee_management.domain.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeDataSourceMapper {
    User toDomain(UserEntity entity);

    UserEntity toEntity(User domain);

}
