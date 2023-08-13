package com.teksen.personmanagementsystem.exception.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExceptionResponse {

    private Date timestamp;
    private String message;
    private List<String> errors;
}
