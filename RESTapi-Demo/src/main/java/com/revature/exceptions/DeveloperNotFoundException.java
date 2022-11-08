package com.revature.exceptions;

public class DeveloperNotFoundException  extends RuntimeException {
    public DeveloperNotFoundException(String message) {
        super(message);
    }
}
