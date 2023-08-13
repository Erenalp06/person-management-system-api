package com.teksen.personmanagementsystem.exception.custom;

import org.springframework.http.HttpStatusCode;

public class PersonNotFoundException extends RuntimeException{

    private HttpStatusCode statusCode;

    public PersonNotFoundException(String message, HttpStatusCode statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public PersonNotFoundException(String message, Throwable cause, HttpStatusCode statusCode) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    public PersonNotFoundException(Throwable cause, HttpStatusCode statusCode) {
        super(cause);
        this.statusCode = statusCode;
    }

    public PersonNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, HttpStatusCode statusCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.statusCode = statusCode;
    }

    public HttpStatusCode getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatusCode statusCode) {
        this.statusCode = statusCode;
    }
}
