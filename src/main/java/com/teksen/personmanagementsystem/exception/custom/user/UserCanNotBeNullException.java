package com.teksen.personmanagementsystem.exception.custom.user;

import com.teksen.personmanagementsystem.exception.custom.CustomException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
public class UserCanNotBeNullException extends CustomException {

    public UserCanNotBeNullException(String message, HttpStatusCode statusCode) {
        super(message, statusCode);
    }
}
