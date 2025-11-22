package com.business.exceptions;

public class LimitExceededException extends BusinessException {
    public LimitExceededException(String message) {
        super(message);
    }
}
