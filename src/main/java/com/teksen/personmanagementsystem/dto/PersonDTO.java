package com.teksen.personmanagementsystem.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonDTO {
    private Long id;

    @NotBlank(message = "First name cannot be blank")
    private String firstName;
    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @Email(message = "Email should be valid")
    private String email;

    @PastOrPresent(message = "Birth date should be past or present")
    @NotNull(message = "Birth date cannot be null")
    private LocalDate birthDate;
}
