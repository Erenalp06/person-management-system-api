package com.teksen.personmanagementsystem.exception;

import com.teksen.personmanagementsystem.exception.custom.*;
import com.teksen.personmanagementsystem.exception.response.DefaultResponse;
import com.teksen.personmanagementsystem.exception.response.ExceptionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ResponseStatusException.class})
    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException e){
        HttpStatusCode statusCode = e.getStatusCode();
        String message = e.getReason();
        return new ResponseEntity<>(message, statusCode);
    }

    @ExceptionHandler(value = {CustomException.class})
    public ResponseEntity<DefaultResponse> handleCustomException(CustomException e){
        DefaultResponse defaultResponse = createDefaultResponseFromException(e, e.getStatusCode().value());
        return new ResponseEntity<>(defaultResponse, e.getStatusCode());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        List<String> errors = new ArrayList<>();

        for(FieldError error : ex.getBindingResult().getFieldErrors())
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        for(ObjectError error : ex.getBindingResult().getGlobalErrors())
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());

        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .timestamp(new java.util.Date())
                .message("Validation failed")
                .errors(errors)
                .build();

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    private static DefaultResponse createDefaultResponseFromException(Exception e, int status) {
        String exceptionType = e.getClass().getSimpleName();
        return DefaultResponse.builder()
                .message(e.getMessage())
                .exceptionType(exceptionType)
                .status(status)
                .timestamp(new Date())
                .build();
    }
}
