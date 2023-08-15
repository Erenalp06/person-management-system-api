package com.teksen.personmanagementsystem.exception.custom.person;

import com.teksen.personmanagementsystem.exception.custom.CustomException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
public class PersonIdCanNotBeNullException extends CustomException {

    public PersonIdCanNotBeNullException(String message, HttpStatusCode statusCode) {
        super(message, statusCode);
    }
}
