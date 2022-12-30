package com.lifehelper.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handler(Exception e) {
        e.printStackTrace();
        Map<String, Object> errors = new HashMap<>();
        if (e instanceof InvalidDataException) {
            errors = ((InvalidDataException) e).getErrorsMap();
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        } else {
            try (StringWriter sw = new StringWriter()) {
                e.printStackTrace(new PrintWriter(sw));
                errors.put("error",sw.toString());
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            } catch (IOException e1) {
                errors.put("error","error exception handler");
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            }
        }
    }

}
