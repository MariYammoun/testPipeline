package com.employee_management.domain.exceptions;

/**
 * @author $(USER)
 **/
public class InvalidAbsenceException extends RuntimeException {
    public InvalidAbsenceException(String message) {
        super(message);
    }
}