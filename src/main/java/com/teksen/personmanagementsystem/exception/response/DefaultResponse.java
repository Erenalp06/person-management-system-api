package com.teksen.personmanagementsystem.exception.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DefaultResponse {
    private int status;
    private String exceptionType;
    private String message;
    private String timestamp;
}
