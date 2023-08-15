package com.teksen.personmanagementsystem.util;

import com.teksen.personmanagementsystem.dto.AuthorityDTO;
import com.teksen.personmanagementsystem.entity.Authority;

public class AuthorityMapper {

    public static AuthorityDTO toDTO(Authority authority){
        if(authority == null)
            return null;
        return AuthorityDTO.builder()
                .id(authority.getId())
                .username(authority.getUser().getUsername())
                .role(authority.getRole())
                .build();
    }

    public static Authority toEntity(AuthorityDTO authorityDTO){
        if(authorityDTO.getId() == null){
            authorityDTO.setId(0L);
        }
        return Authority.builder()
                .id(authorityDTO.getId())
                .user(null)
                .role(authorityDTO.getRole())
                .build();
    }
}
