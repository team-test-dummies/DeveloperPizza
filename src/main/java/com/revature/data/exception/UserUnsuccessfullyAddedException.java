package com.revature.data.exception;

public class UserUnsuccessfullyAddedException extends RuntimeException {
    public UserUnsuccessfullyAddedException(String message) {
        super(message);
    }
}
