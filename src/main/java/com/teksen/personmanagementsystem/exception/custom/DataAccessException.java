package com.teksen.personmanagementsystem.exception.custom;

import org.springframework.http.HttpStatusCode;

public class DataAccessException extends CustomException{

    public DataAccessException(String message, HttpStatusCode statusCode) {
        super(message, statusCode);
    }
}
