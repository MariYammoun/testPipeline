package com.employee_management.domain.exceptions;

/**
 * @author $(USER)
 **/
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
