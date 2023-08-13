package com.teksen.personmanagementsystem.exception.custom;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class PersonCanNotBeNullException extends RuntimeException{

    private HttpStatus statusCode;

    public PersonCanNotBeNullException(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public PersonCanNotBeNullException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public PersonCanNotBeNullException(String message, Throwable cause, HttpStatus statusCode) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    public PersonCanNotBeNullException(Throwable cause, HttpStatus statusCode) {
        super(cause);
        this.statusCode = statusCode;
    }
}
