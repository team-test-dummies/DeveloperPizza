package com.revature.exceptions;

public class EmployeeUnsuccessfullyAddedException extends RuntimeException {
    public EmployeeUnsuccessfullyAddedException(String message) {
        super(message);
    }
}
