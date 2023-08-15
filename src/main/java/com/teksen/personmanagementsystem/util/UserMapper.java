package com.teksen.personmanagementsystem.util;

import com.teksen.personmanagementsystem.dto.UserDTO;
import com.teksen.personmanagementsystem.entity.User;

public class UserMapper {

    public static UserDTO toDTO(User user){
        if(user == null)
            return null;
        return UserDTO.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .enabled(user.getEnabled())
                .build();
    }

    public static User toEntity(UserDTO userDTO){
        return User.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .enabled(userDTO.getEnabled())
                .build();
    }
}
