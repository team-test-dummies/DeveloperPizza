package com.revature.exception;

import com.revature.dto.Message;

public class RegisterException extends RuntimeException {
    public RegisterException(String message) {
        super(message);
    }
}
