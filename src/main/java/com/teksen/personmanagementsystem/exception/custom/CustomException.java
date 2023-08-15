package com.teksen.personmanagementsystem.exception.custom;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
public class CustomException extends RuntimeException{
    private final HttpStatusCode statusCode;

    public CustomException(String message, HttpStatusCode statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

}
