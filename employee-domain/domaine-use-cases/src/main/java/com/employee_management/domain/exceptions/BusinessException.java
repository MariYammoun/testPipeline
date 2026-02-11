package com.employee_management.domain.exceptions;

/**
 * @author $(USER)
 **/
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}

