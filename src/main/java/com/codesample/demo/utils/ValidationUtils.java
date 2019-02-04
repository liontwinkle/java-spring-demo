package com.codesample.demo.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.LinkedList;
import java.util.List;

public class ValidationUtils {

    public static List<String> buildErrors(BindingResult bindingResult) {
        List<String> errorFields = new LinkedList<>();
        StringBuilder unknownErrorsMsgBuilder = new StringBuilder();
        try {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error : errors) {
                if (error instanceof FieldError) {
                    errorFields.add(((FieldError) error).getField());
                } else {
                    unknownErrorsMsgBuilder.append(error.getDefaultMessage()).append(";");
                }
            }
            if(unknownErrorsMsgBuilder.length() != 0) {
                errorFields.add(unknownErrorsMsgBuilder.toString());
            }
        } catch (Exception e) {
            errorFields.add("fail_to_build_validation_errors");
        }

        return errorFields;
    }
}
