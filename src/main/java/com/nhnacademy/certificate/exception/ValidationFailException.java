package com.nhnacademy.certificate.exception;

import java.util.stream.Collectors;
import org.springframework.validation.BindingResult;

public class ValidationFailException extends RuntimeException {
    public ValidationFailException(BindingResult bindingResult) {
        super(bindingResult.getAllErrors()
                .stream()
                .map(objectError -> new StringBuilder().append("ObjectName = ").append(objectError.getObjectName())
                        .append(",Message = ").append(objectError.getDefaultMessage())
                        .append(",Code = ").append(objectError.getCode()))
                .collect(Collectors.joining("|")));
    }
}
