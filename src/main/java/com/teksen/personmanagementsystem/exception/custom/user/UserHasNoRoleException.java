package com.teksen.personmanagementsystem.exception.custom.user;

import com.teksen.personmanagementsystem.exception.custom.CustomException;
import org.springframework.http.HttpStatusCode;

public class UserHasNoRoleException extends CustomException {
    public UserHasNoRoleException(String message, HttpStatusCode statusCode) {
        super(message, statusCode);
    }
}
