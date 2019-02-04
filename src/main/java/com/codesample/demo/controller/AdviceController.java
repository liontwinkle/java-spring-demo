package com.codesample.demo.controller;

import com.codesample.demo.exceprion.RestException;
import com.codesample.demo.exceprion.ValidationException;
import com.codesample.demo.mapper.ExceptionMapper;
import com.codesample.demo.request.ExceptionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {
    private Logger log = LoggerFactory.getLogger(AdviceController.class);

    @Autowired
    private ExceptionMapper exceptionMapper;

    @ExceptionHandler(RestException.class)
    public ResponseEntity<ExceptionDto> handleRestException(RestException ex) {
        log.warn("Unhandled error: ", ex);
        return ResponseEntity.status(ex.getResponseCode())
                .body(exceptionMapper.exceptionToDto(ex));
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<ExceptionDto> handleAccessDenied(AccessDeniedException ex) {
        log.warn("Unhandled error: ", ex);
        return ResponseEntity.status(403)
                .body(exceptionMapper.exceptionToDto(ex));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ExceptionDto> handleException(MethodArgumentNotValidException ex) {
        log.warn("Unhandled error: ", ex);
        return ResponseEntity.status(400)
                .body(exceptionMapper.exceptionToDto(new ValidationException(ex)));
    }

    @ExceptionHandler({BindException.class})
    public ResponseEntity<ExceptionDto> handleException(BindException ex) {
        log.warn("Unhandled error: ", ex);
        return ResponseEntity.status(400)
                .body(exceptionMapper.exceptionToDto(new ValidationException(ex)));
    }
}
