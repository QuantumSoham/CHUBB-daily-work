package com.business.exceptions;

/**
 * Base class for all business rule exceptions.
 */
public class BusinessException extends Exception {
    public BusinessException(String message) {
        super(message);
    }
}
