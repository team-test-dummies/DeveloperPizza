package com.revature.exception;

public class OrderUnsuccessfullyAddedException extends RuntimeException {
    public OrderUnsuccessfullyAddedException(String message) {
        super(message);
    }
}
