package com.employee_management.domain.shared.mapping;

import ch.qos.logback.core.model.ComponentModel;
import com.employee_management.domain.absences.dtos.AbsenceResponseDto;
import com.employee_management.domain.entities.Absence;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AbsenceMapper {

    @Mapping(source = "employee.id", target = "employeeId")
    AbsenceResponseDto toDto(Absence absence);

    List<AbsenceResponseDto> toDtoList(List<Absence> absences);

}
