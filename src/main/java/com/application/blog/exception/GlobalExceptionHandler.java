package com.application.blog.exception;

import com.application.blog.dto.ExceptionDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // Application specific exception
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                        WebRequest request) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(LocalDateTime.now(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BlogAPIException.class)
    public ResponseEntity<ExceptionDTO> handleBlogAPIException(BlogAPIException exception,
                                                               WebRequest webRequest){
        ExceptionDTO errorDetails = new ExceptionDTO(LocalDateTime.now(), exception.getMessage(),
                webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    // Global specific exception
    @ExceptionHandler(value = {NumberFormatException.class, Exception.class})
    public ResponseEntity<ExceptionDTO> handleNumberFormatException(Exception exception,
                                                                   WebRequest request) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(LocalDateTime.now(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Bean validation errors
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String,String> errorValidationResultMap = new HashMap<>();
        errorValidationResultMap.put("timestamp", String.valueOf(LocalDateTime.now()));
        ex.getBindingResult().getAllErrors().forEach(error -> {
            FieldError fieldError = (FieldError) error;
            String field = fieldError.getField();
            String message = error.getDefaultMessage();
            errorValidationResultMap.put(field,message);
        });
        return new ResponseEntity<>(errorValidationResultMap, HttpStatus.BAD_REQUEST);
    }
}
