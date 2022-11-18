package com.revature.data.exception;

public class AccountUnsuccessfullyRemovedException extends RuntimeException {
    public AccountUnsuccessfullyRemovedException(String message) {
        super(message);
    }
}
