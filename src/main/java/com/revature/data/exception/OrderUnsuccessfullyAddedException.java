package com.revature.data.enums.exception;

public class OrderUnsuccessfullyAddedException extends RuntimeException {
    public OrderUnsuccessfullyAddedException(String message) {
        super(message);
    }
}
