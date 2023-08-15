package com.teksen.personmanagementsystem.service;

import com.teksen.personmanagementsystem.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll();
    UserDTO findByUsername(String username);
    UserDTO save(UserDTO userDTO);
    UserDTO update(UserDTO userDTO);
    String deleteByUsername(String username);
}
