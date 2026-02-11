package com.employee_management.domain.authentification.use_cases;

import com.employee_management.domain.authentification.dtos.AuthResponseDto;
import com.employee_management.domain.authentification.dtos.LoginRequestDto;

/**
 * @author $(USER)
 **/
public interface ILoginUseCase {
    AuthResponseDto login(LoginRequestDto request);
}
