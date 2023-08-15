package com.teksen.personmanagementsystem.exception.custom.authority;

import com.teksen.personmanagementsystem.exception.custom.CustomException;
import org.springframework.http.HttpStatusCode;

public class InvalidRoleException extends CustomException {
    public InvalidRoleException(String message, HttpStatusCode statusCode) {
        super(message, statusCode);
    }
}
