package com.employee_management.domain.authentification.use_cases;

import com.employee_management.domain.authentification.dtos.ChangePasswordRequestDto;

/**
 * @author $(USER)
 **/
public interface IChangePasswordUseCase {
    void execute(Long userId, ChangePasswordRequestDto request);
}
