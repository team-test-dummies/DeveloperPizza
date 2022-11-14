package com.revature.exception;

public class AccountUnsuccessfullyEditedException extends RuntimeException {
    public AccountUnsuccessfullyEditedException(String message) {
        super(message);
    }

}
