package com.hrd.jpahibernate.exception;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ProblemDetail handleProductNotFoundException(ProductNotFoundException ex){
        ProblemDetail detail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        detail.setTitle("Not Found");
        detail.setProperty("timestamp", LocalDateTime.now());
        return detail;
    }

    // @RequestBody Object
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ProblemDetail detail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        detail.setProperty("errors", errors);
        detail.setProperty("Timestamp", LocalDateTime.now());

        return detail;
    }

    // Method Parameters
    @ExceptionHandler(HandlerMethodValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handHandlerMethodValidationException(HandlerMethodValidationException e) {
        Map<String,String> errors  = new HashMap<>();

        for (MessageSourceResolvable messageSourceResolvable : e.getAllErrors()) {
            if (messageSourceResolvable.getCodes() != null) {
                System.out.println(messageSourceResolvable.getCodes()[1]);
                errors.put(messageSourceResolvable.getCodes()[1]
                        .split("\\.")[1], messageSourceResolvable.getDefaultMessage());
            }
        }
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatusCode.valueOf(404));
        problemDetail.setProperty("errors",errors);
        return problemDetail;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", HttpStatus.BAD_REQUEST.value());
        error.put("error", "Invalid request format");
        error.put("message", "Invalid number format for 'quantity'");
        return error;
    }


}
