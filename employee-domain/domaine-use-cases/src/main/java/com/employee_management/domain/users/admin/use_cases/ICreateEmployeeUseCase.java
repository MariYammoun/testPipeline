package com.employee_management.domain.users.admin.use_cases;

import com.employee_management.domain.users.admin.dtos.CreateEmployeeRequestDto;
import com.employee_management.domain.entities.User;


public interface ICreateEmployeeUseCase {

    User execute(CreateEmployeeRequestDto request);


}
