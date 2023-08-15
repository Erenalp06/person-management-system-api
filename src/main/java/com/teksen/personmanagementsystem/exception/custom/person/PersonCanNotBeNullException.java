package com.teksen.personmanagementsystem.exception.custom.person;

import com.teksen.personmanagementsystem.exception.custom.CustomException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
public class PersonCanNotBeNullException extends CustomException {
    public PersonCanNotBeNullException(String message, HttpStatusCode statusCode) {
        super(message, statusCode);
    }
}
