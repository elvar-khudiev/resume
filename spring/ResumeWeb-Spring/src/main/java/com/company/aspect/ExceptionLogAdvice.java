package com.company.aspect;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionLogAdvice {

//    @ExceptionHandler(Exception.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public String handleValidationException(final Exception ex) {
//        return "error";
//    }
}
