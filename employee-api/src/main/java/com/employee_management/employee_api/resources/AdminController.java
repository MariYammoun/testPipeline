package com.employee_management.employee_api.resources;

import com.employee_management.domain.entities.enums.RoleType;
import com.employee_management.domain.users.admin.dtos.CreateEmployeeRequestDto;
import com.employee_management.domain.users.admin.use_cases.ICreateEmployeeUseCase;
import com.employee_management.domain.users.admin.use_cases.IDeleteUserUseCase;
import com.employee_management.domain.users.admin.use_cases.IGetAllUsersUseCase;
import com.employee_management.domain.users.admin.use_cases.IUpdateUserRoleUseCase;
import com.employee_management.domain.users.dtos.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class AdminController {

    private final ICreateEmployeeUseCase createEmployeeUseCase;
    private final IGetAllUsersUseCase  getAllUsersUseCase;
    private final IUpdateUserRoleUseCase updateUserRoleUseCase;
    private final IDeleteUserUseCase deleteUserUseCase;

    @PostMapping
    public ResponseEntity<String> createEmployee(@RequestBody CreateEmployeeRequestDto request) {
        createEmployeeUseCase.execute(request);
        return ResponseEntity.ok("Employé créé. Mot de passe envoyé.");
    }
//lister les users
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(getAllUsersUseCase.execute());
    }

    //supprimer user

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        deleteUserUseCase.execute(userId);
        return ResponseEntity.ok("User supprimé.");
    }
    @PatchMapping("/{userId}/role")
    public ResponseEntity<String> updateUserRole(@PathVariable Long userId, @RequestParam RoleType newRole) {
        updateUserRoleUseCase.execute(userId, newRole);
        return ResponseEntity.ok("role mis à jour vers :" + newRole);
    }
}

