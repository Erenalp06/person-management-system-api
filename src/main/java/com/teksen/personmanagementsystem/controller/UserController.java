package com.teksen.personmanagementsystem.controller;

import com.teksen.personmanagementsystem.dto.UserDTO;
import com.teksen.personmanagementsystem.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
@AllArgsConstructor
@Tag(name = "User", description = "User Management System API")
@SecurityRequirement(name = "personmsapi")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDTO> findAll(){
        return userService.findAll();
    }

    @GetMapping("{username}")
    public UserDTO findByUsername(@PathVariable String username){
        return userService.findByUsername(username);
    }

    @PostMapping
    public UserDTO add(@Valid @RequestBody UserDTO userDTO){
        return userService.save(userDTO);
    }

    @PutMapping
    public UserDTO update(@RequestBody UserDTO userDTO){
        return userService.save(userDTO);
    }

    @DeleteMapping("{username}")
    public ResponseEntity<String> deleteByUsername(@PathVariable String username){
        return new ResponseEntity<>(userService.deleteByUsername(username), HttpStatus.OK);
    }

}
