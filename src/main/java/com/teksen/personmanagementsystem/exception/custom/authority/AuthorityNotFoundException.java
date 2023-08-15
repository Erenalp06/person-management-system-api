package com.teksen.personmanagementsystem.exception.custom.authority;

import com.teksen.personmanagementsystem.exception.custom.CustomException;
import org.springframework.http.HttpStatusCode;

public class AuthorityNotFoundException extends CustomException {

    public AuthorityNotFoundException(String message, HttpStatusCode statusCode) {
        super(message, statusCode);
    }
}
