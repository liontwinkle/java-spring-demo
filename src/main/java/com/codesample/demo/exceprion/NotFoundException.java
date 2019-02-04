package com.codesample.demo.exceprion;

public class NotFoundException extends RestException {
    public NotFoundException(String message) {
        super(message, 404);
    }
}
