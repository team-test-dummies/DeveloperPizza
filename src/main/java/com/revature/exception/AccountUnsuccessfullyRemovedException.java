package com.revature.exception;

public class AccountUnsuccessfullyRemovedException extends RuntimeException {
    public AccountUnsuccessfullyRemovedException(String message) {
        super(message);
    }
}
