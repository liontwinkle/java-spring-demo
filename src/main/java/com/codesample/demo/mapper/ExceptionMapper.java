package com.codesample.demo.mapper;

import com.codesample.demo.exceprion.RestException;
import com.codesample.demo.exceprion.ValidationException;
import com.codesample.demo.request.ExceptionDto;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Mapper(componentModel = "spring")
public abstract class ExceptionMapper {
    @Mappings({
            @Mapping(target = "message", expression = "java(ex.getMessage())")
    })
    public abstract ExceptionDto exceptionToDto(ValidationException ex);

    @Mappings({
            @Mapping(target = "message", expression = "java(ex.getMessage())")
    })
    public abstract ExceptionDto exceptionToDto(RestException ex);

    @Mappings({
            @Mapping(target = "message", expression = "java(ex.getMessage())"),
    })
    public abstract ExceptionDto exceptionToDto(Exception ex);

    @AfterMapping
    protected void putValidationErrors(@MappingTarget ExceptionDto dto, ValidationException ex) {
        Map<String, Object> additionalData = new HashMap<>();
        additionalData.put("invalidFields", ex.getErrors());
        dto.setAdditionalData(additionalData);
    }
}
