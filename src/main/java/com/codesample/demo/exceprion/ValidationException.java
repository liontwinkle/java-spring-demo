package com.codesample.demo.exceprion;

import com.codesample.demo.utils.ValidationUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Collections;
import java.util.List;

public class ValidationException extends RestException {
    private List<String> errors;
    public ValidationException(MethodArgumentNotValidException ex) {
        super("Invalid request.", 400);
        errors = ValidationUtils.buildErrors(ex.getBindingResult());
    }

    public ValidationException(String message, String field) {
        super(message, 400);
        errors = Collections.singletonList(field);
    }

    public ValidationException (String message) {
        super(message, 400);
    }

    public ValidationException(BindException ex) {
        super("Invalid request.", 400);
        errors = ValidationUtils.buildErrors(ex);
    }

    public List<String> getErrors() {
        return errors;
    }
}
