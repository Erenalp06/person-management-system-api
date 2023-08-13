package com.teksen.personmanagementsystem.exception.custom;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
public class PersonIdCanNotBeNullException extends RuntimeException{

    private HttpStatusCode statusCode;

    public PersonIdCanNotBeNullException(HttpStatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public PersonIdCanNotBeNullException(String message, HttpStatusCode statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public PersonIdCanNotBeNullException(String message, Throwable cause, HttpStatusCode statusCode) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    public PersonIdCanNotBeNullException(Throwable cause, HttpStatusCode statusCode) {
        super(cause);
        this.statusCode = statusCode;
    }
}
