package com.codesample.demo.request;

import java.util.Map;

public class ExceptionDto {
    protected String message;
    protected Map<String, Object> additionalData;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(Map<String, Object> additionalData) {
        this.additionalData = additionalData;
    }
}
