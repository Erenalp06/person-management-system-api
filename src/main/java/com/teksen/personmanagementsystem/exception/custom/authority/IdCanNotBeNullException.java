package com.teksen.personmanagementsystem.exception.custom.authority;

import com.teksen.personmanagementsystem.exception.custom.CustomException;
import org.springframework.http.HttpStatusCode;

public class IdCanNotBeNullException extends CustomException {
    public IdCanNotBeNullException(String message, HttpStatusCode statusCode) {
        super(message, statusCode);
    }
}
