package com.teksen.personmanagementsystem.controller;

import com.teksen.personmanagementsystem.dto.AuthorityDTO;
import com.teksen.personmanagementsystem.service.AuthorityService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/authority")
@AllArgsConstructor
@Tag(name = "Authority", description = "Authority Management")
@SecurityRequirement(name = "personmsapi")
public class AuthorityController {

    private final AuthorityService authorityService;

    @GetMapping("{username}")
    public List<AuthorityDTO> findAuthorityByUsername(@PathVariable String username){
        return authorityService.findRolesByUsername(username);
    }

    @PostMapping
    public ResponseEntity<AuthorityDTO> add(@RequestBody AuthorityDTO authorityDTO){
        return new ResponseEntity<>(authorityService.save(authorityDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<AuthorityDTO> update(@RequestBody AuthorityDTO authorityDTO){
        return ResponseEntity.ok(authorityService.save(authorityDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteByUsername(@PathVariable Long id){
        return new ResponseEntity<>(authorityService.delete(id), HttpStatus.OK);
    }
}
