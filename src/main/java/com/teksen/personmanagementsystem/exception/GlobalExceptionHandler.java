package com.teksen.personmanagementsystem.exception;

import com.teksen.personmanagementsystem.exception.custom.PersonCanNotBeNullException;
import com.teksen.personmanagementsystem.exception.custom.PersonIdCanNotBeNullException;
import com.teksen.personmanagementsystem.exception.custom.PersonNotFoundException;
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

    @ExceptionHandler(value = {PersonNotFoundException.class})
    public ResponseEntity<DefaultResponse> handlePersonNotFoundException(PersonNotFoundException e){
        String exceptionType = e.getClass().getSimpleName();
        int status = e.getStatusCode().value();
        DefaultResponse defaultResponse = DefaultResponse.builder()
                .message(e.getMessage())
                .exceptionType(exceptionType)
                .status(status)
                .build();
        return new ResponseEntity<>(defaultResponse, e.getStatusCode());
    }

    @ExceptionHandler(value = {PersonCanNotBeNullException.class})
    public ResponseEntity<DefaultResponse> handlePersonCanNotBeNullException(PersonCanNotBeNullException e){
        String exceptionType = e.getClass().getSimpleName();
        int status = e.getStatusCode().value();
        DefaultResponse defaultResponse = DefaultResponse.builder()
                .message(e.getMessage())
                .exceptionType(exceptionType)
                .status(status)
                .build();
        return new ResponseEntity<>(defaultResponse, e.getStatusCode());
    }

    @ExceptionHandler(value = {PersonIdCanNotBeNullException.class})
    public ResponseEntity<DefaultResponse> handlePersonIdCanNotBeNullException(PersonIdCanNotBeNullException e){
        String exceptionType = e.getClass().getSimpleName();
        int status = e.getStatusCode().value();
        DefaultResponse defaultResponse = DefaultResponse.builder()
                .message(e.getMessage())
                .exceptionType(exceptionType)
                .status(status)
                .build();
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
}
