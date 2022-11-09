package com.revature.exceptions;

public class EmployeeNotFoundException extends RuntimeException  {
    public EmployeeNotFoundException(String message){
        super(message);
    }
}
