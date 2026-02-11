package com.employee_management.domain.users.admin.use_cases;

import com.employee_management.domain.entities.enums.RoleType;

public interface IUpdateUserRoleUseCase {
    void execute(Long userId, RoleType newRole);
}
