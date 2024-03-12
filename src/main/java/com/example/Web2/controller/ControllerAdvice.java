package com.example.Web2.controller;

import com.example.Web2.exception.EntityNotFoundException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Locale;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> entityNotFound(EntityNotFoundException ex, ApplicationContext context){
        MessageSource messageSource = (MessageSource)context.getBean("messageSource");
        String mess =  messageSource.getMessage("project.notFound.byId",
                new Object[]{ex.getId()},
                Locale.ROOT);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
