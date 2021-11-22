package com.currencyexchangerate.exchangeservice2.exception;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class ServiceExceptionHandler {

    protected final Log logger = LogFactory.getLog(this.getClass());

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<ApiException> handleAccessDeniedException(AccessDeniedException ex) { //,HttpHeaders headers, WebRequest request
        //1. Create a payload
        String error = "Access denied";
        ApiException apiException = new ApiException(HttpStatus.FORBIDDEN, error, ex);
        //2. Return response entity
        return buildResponseEntity(apiException);
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ResponseEntity<ApiException> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        //1. Create a payload
        String error = "Page not found";
        ApiException apiException = new ApiException(HttpStatus.NOT_FOUND, error, ex);
        //2. Return response entity
        return buildResponseEntity(apiException);
    }

    @ExceptionHandler(value = DataNotFoundException.class)
    public ResponseEntity<ApiException> handleDataNotFoundException(DataNotFoundException ex) {
        //1. Create a payload
        ApiException apiException = new ApiException(HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        //2. Return response entity
        return buildResponseEntity(apiException);
    }


    private ResponseEntity<ApiException> buildResponseEntity(ApiException apiError) {
        logger.error(apiError.getDebugMessage());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}