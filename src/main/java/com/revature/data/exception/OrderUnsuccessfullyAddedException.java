package com.revature.data.exception;

public class OrderUnsuccessfullyAddedException extends RuntimeException {
    public OrderUnsuccessfullyAddedException(String message) {
        super(message);
    }
}
