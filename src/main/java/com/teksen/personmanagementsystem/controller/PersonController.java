package com.teksen.personmanagementsystem.controller;

import com.teksen.personmanagementsystem.dto.PersonDTO;
import com.teksen.personmanagementsystem.service.PersonService;
import com.teksen.personmanagementsystem.service.impl.PersonIntegrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/person")
@AllArgsConstructor
@Tag(name = "Person", description = "Person Management System API")
@SecurityRequirement(name = "personmsapi")
public class PersonController {

    private final PersonService personService;


    @GetMapping
    @Operation(
            summary = "Get all people",
            description = "Get all people from database"
    )

    public ResponseEntity<List<PersonDTO>> findAll(){
        return ResponseEntity.ok(personService.findAll());
    }

    @GetMapping(path = "{id}")
    @Operation(
            summary = "Get person by id",
            description = "first looks at the database and then at the external api source"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the person"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied"),
            @ApiResponse(responseCode = "404", description = "Person not found")
    })
    public ResponseEntity<PersonDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(personService.findById(id));
    }

    @PostMapping
    @Operation(
            summary = "Save person",
            description = "Save person to database"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Person created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "Person already exists")
    })
    public ResponseEntity<PersonDTO> save(@Valid @RequestBody PersonDTO personDTO){
        return ResponseEntity.status(201).body(personService.save(personDTO));
    }

    @PutMapping
    @Operation(
            summary = "Update person",
            description = "Update person to database"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Person updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Person not found")
    })
    public ResponseEntity<PersonDTO> update(@RequestBody PersonDTO personDTO){
        return ResponseEntity.status(201).body(personService.update(personDTO));
    }

    @DeleteMapping(path = "{id}")
    @Operation(
            summary = "Delete person by id",
            description = "Delete person by id from database"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person deleted"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied"),
            @ApiResponse(responseCode = "404", description = "Person not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        personService.deleteById(id);
        return ResponseEntity.ok("Person deleted");
    }



    // TODO add unit tests and integration tests
    // TODO just look at pagination
    // TODO monitor the application with Actuator
    // TODO rate limit the API

}
