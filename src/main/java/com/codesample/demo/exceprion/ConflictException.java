package com.codesample.demo.exceprion;

public class ConflictException extends RestException {
    public ConflictException(String message) {
        super(message, 409);
    }
}
