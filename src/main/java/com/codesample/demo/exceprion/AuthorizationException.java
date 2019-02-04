package com.codesample.demo.exceprion;

import java.util.Map;

public class AuthorizationException extends RestException {

    public AuthorizationException(String message) {
        super(message, 403);
    }

    public AuthorizationException(String message, Map<String, Object> additionalData) {
        super(message, 403, additionalData);
    }
}
