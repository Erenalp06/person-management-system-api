package com.teksen.personmanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorityDTO {

    private Long id;
    private String username;
    private String role;

    @JsonIgnore
    public Boolean isEmpty(){
        return this.id == null && this.username == null && this.role == null;
    }
}
