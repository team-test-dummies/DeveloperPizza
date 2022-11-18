package com.revature.data.exception;

public class UserNotFoundException extends RuntimeException  {
    public UserNotFoundException(String message){
        super(message);
    }
}
