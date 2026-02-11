package com.employee_management.domain.users.admin.use_cases;

import com.employee_management.domain.users.dtos.UserResponseDto;

import java.util.List;

/**
 * @author $(USER)
 **/
public interface IGetAllUsersUseCase {

    List<UserResponseDto> execute();
}
