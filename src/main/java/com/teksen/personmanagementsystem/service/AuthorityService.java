package com.teksen.personmanagementsystem.service;

import com.teksen.personmanagementsystem.dto.AuthorityDTO;
import com.teksen.personmanagementsystem.entity.Authority;

import java.util.List;

public interface AuthorityService {

    List<AuthorityDTO> findRolesByUsername(String username);
    AuthorityDTO save(AuthorityDTO authorityDTO);
    AuthorityDTO update(AuthorityDTO authorityDTO);
    String delete(Long id);
}
