package com.teksen.personmanagementsystem.service.impl;

import com.teksen.personmanagementsystem.dto.PersonDTO;
import com.teksen.personmanagementsystem.exception.custom.PersonNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class ExternalApiService {

    private final RestTemplate restTemplate;
    private static final String API_BASE_URL = "https://my-json-server.typicode.com/Erenalp06/person";
    private final Logger logger = LoggerFactory.getLogger(ExternalApiService.class);

    public ExternalApiService() {
        this.restTemplate = new RestTemplate();
    }


    @Async
    public CompletableFuture<PersonDTO> getPeople(Long id){
        String apiUrl = API_BASE_URL + "/people/" + id;
        PersonDTO response = restTemplate.getForEntity(apiUrl, PersonDTO.class).getBody();
        if(response == null)
            throw new PersonNotFoundException("Person not found with id: " + id, HttpStatus.NOT_FOUND);
        logger.info("Person called with id from external : {}", id);
        return CompletableFuture.completedFuture(response);
    }
}
