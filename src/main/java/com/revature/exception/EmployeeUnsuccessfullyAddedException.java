package com.revature.exception;

public class EmployeeUnsuccessfullyAddedException extends RuntimeException {
    public EmployeeUnsuccessfullyAddedException(String message) {
        super(message);
    }
}
