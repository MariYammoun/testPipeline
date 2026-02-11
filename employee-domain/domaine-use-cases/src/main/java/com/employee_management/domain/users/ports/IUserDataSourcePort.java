package com.employee_management.domain.users.ports;

import com.employee_management.domain.entities.User;

import java.util.List;
import java.util.Optional;


public interface IUserDataSourcePort {

    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    List<User> findAll();
    void deleteById(Long id);
    Optional<User> findByUsername(String username);
}
