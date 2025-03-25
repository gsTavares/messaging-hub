package com.messaginghub.be.messaging.messages.controller.exception;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.messaginghub.be.core.util.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex,
            WebRequest request) {
        String message = "Unexpected error";

        if (ex.getMessage().contains("duplicate")) {
            message = "An unique constraint was violated";
        }

        ApiResponse<String> response = new ApiResponse<>(message, "Error type: data integrity");
        return handleExceptionInternal(ex, response, null, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(InvalidDataOperationException.class)
    protected ResponseEntity<Object> handleInvalidDataOperationException(InvalidDataOperationException ex, WebRequest request) {
        ApiResponse<String> response = new ApiResponse<String>(ex.getMessage(), "Error type: invalid data operation");
        return handleExceptionInternal(ex, response, null, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex, WebRequest request) {
        ApiResponse<String> response = new ApiResponse<String>(ex.getMessage(), "Error type: no such element");
        return handleExceptionInternal(ex, response, null, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ApiResponse<List<String>> response = new ApiResponse<>(ex.getAllErrors().stream().map(ObjectError::getDefaultMessage).toList(), "Error type: invalid fields");
        return handleExceptionInternal(ex, response, headers, status, request);
    }

}
