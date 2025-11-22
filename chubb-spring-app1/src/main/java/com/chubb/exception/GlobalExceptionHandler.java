package com.chubb.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.validation.FieldError;


@RestControllerAdvice
public class GlobalExceptionHandler {

    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();

        // For each field error, put (fieldName -> defaultMessage) in a simple map
        ex.getBindingResult().getFieldErrors().forEach((FieldError error) -> {
            String fieldName = error.getField();
            String message = error.getDefaultMessage();
            errorMap.put(fieldName, message);
        });

        return errorMap;
    }

    // Optional: a fallback for any other exceptions you want to expose as JSON
    @ExceptionHandler(Exception.class)
    public Map<String, String> handleAllExceptions(Exception ex) {
        Map<String, String> map = new HashMap<>();
        map.put("error", ex.getMessage() == null ? "unexpected error" : ex.getMessage());
        return map;
    }
}
