package com.codesample.demo.exceprion;

import java.util.HashMap;
import java.util.Map;

public class RestException extends RuntimeException {
    private int responseCode;
    private Map<String, Object> additionalData = new HashMap<>();

    public RestException(int responseCode) {
        super();
        this.responseCode = responseCode;
    }

    public RestException(String message, Throwable throwable) {
        super(message, throwable);
        responseCode = 500;
    }

    public RestException(String message, int responseCode) {
        super(message);
        this.responseCode = responseCode;
    }

    public RestException(String message, int responseCode, Throwable throwable) {
        super(message, throwable);
        this.responseCode = responseCode;
    }

    public RestException(String message, int responseCode, Map<String, Object> additionalData) {
        super(message);
        this.responseCode = responseCode;
        this.additionalData = additionalData;
    }

    public RestException(String message, int responseCode, Throwable throwable, Map<String, Object> additionalData) {
        super(message, throwable);
        this.responseCode = responseCode;
        this.additionalData = additionalData;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public Map<String, Object> getAdditionalData() {
        return additionalData;
    }
}
