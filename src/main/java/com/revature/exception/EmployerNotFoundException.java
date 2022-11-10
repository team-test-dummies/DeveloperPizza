package com.revature.exception;

public class EmployerNotFoundException extends RuntimeException  {
    public EmployerNotFoundException(String message){
        super(message);
    }
}
