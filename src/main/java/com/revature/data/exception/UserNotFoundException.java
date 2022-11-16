package com.revature.data.enums.exception;

public class UserNotFoundException extends RuntimeException  {
    public UserNotFoundException(String message){
        super(message);
    }
}
