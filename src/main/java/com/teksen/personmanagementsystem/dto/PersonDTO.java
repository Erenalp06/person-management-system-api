package com.teksen.personmanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "First name of the person", example = "John", required = true)
    private String firstName;
    @NotBlank(message = "Last name cannot be blank")
    @Schema(description = "Last name of the person", example = "Doe", required = true)
    private String lastName;

    @Email(message = "Email should be valid")
    @Schema(description = "Email of the person", example = "johndoe@gmail.com")
    private String email;

    @PastOrPresent(message = "Birth date should be past or present")
    @NotNull(message = "Birth date cannot be null")
    @Schema(description = "Birth date of the person", example = "1990-01-01")
    private LocalDate birthDate;

    @JsonIgnore
    public boolean isEmpty() {
        return firstName == null && lastName == null && email == null && birthDate == null;
    }
}
