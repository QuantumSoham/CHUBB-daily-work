package com.business.exceptions;

public class InvalidAccountException extends BusinessException {
    public InvalidAccountException(String message) {
        super(message);
    }
}
