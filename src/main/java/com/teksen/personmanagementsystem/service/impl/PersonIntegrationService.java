package com.teksen.personmanagementsystem.service.impl;

import com.teksen.personmanagementsystem.dto.PersonDTO;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class PersonIntegrationService {

    private final ExternalApiService externalApiService;

    /*public PersonDTO fetchPeopleById(Long id){
        return externalApiService.getPeople(id);
    }*/

    @Async
    public CompletableFuture<PersonDTO> fetchPeopleByIdAsync(Long id){
        return externalApiService.getPeople(id);
    }
}
