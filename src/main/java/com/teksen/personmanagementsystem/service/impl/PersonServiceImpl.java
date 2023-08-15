package com.teksen.personmanagementsystem.service.impl;

import com.teksen.personmanagementsystem.dto.PersonDTO;
import com.teksen.personmanagementsystem.entity.Person;
import com.teksen.personmanagementsystem.exception.custom.person.PersonCanNotBeNullException;
import com.teksen.personmanagementsystem.exception.custom.person.PersonIdCanNotBeNullException;
import com.teksen.personmanagementsystem.exception.custom.person.PersonNotFoundException;
import com.teksen.personmanagementsystem.repository.PersonRepository;
import com.teksen.personmanagementsystem.service.PersonService;
import com.teksen.personmanagementsystem.util.PersonMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);
    private static final String PERSON_NOT_FOUND = "Person not found with id %d";
    private static final String PERSON_CAN_NOT_BE_NULL = "Person can not be null";
    private static final String CACHE_NAME = "personCache";
    private final PersonIntegrationService personIntegrationService;
    private final CacheService cacheService;


    @Override
    @Cacheable(cacheNames = CACHE_NAME)
    public List<PersonDTO> findAll() {
        simulateBackendCall();
        logger.info("Getting all persons from cache or database");
        List<PersonDTO> persons = personRepository.findAll()
                .stream()
                .map(PersonMapper::toDTO).toList();
        logger.info("Retrieved all persons from database");
        return persons;
    }

    @Override
    @Cacheable(cacheNames = CACHE_NAME, key = "#id")
    public PersonDTO findById(Long id) {

        Person person = personRepository.findById(id).orElse(null);
        if (person == null) {
            person = fetchAndSavePersonFromExternalAPI(id);

        }
        logger.info("Person called with id : {}", id);
        return PersonMapper.toDTO(person);

    }

    @Transactional
    public Person fetchAndSavePersonFromExternalAPI(Long id) {
        Person person = PersonMapper.toEntity(personIntegrationService.fetchPeopleByIdAsync(id).join());
        if (person == null) {
            logger.error("Person not found with id : {}", id);
            throw new PersonNotFoundException(String.format(PERSON_NOT_FOUND, id), HttpStatus.NOT_FOUND);
        }
        cacheService.clearCache(CACHE_NAME);
        person = personRepository.save(person);
        return person;
    }

    @Override
    @CachePut(cacheNames = CACHE_NAME, key = "#personDTO.id")
    public PersonDTO update(PersonDTO personDTO) {

        if(personDTO == null || personDTO.isEmpty()){
            logger.error(PERSON_CAN_NOT_BE_NULL);
            throw new PersonNotFoundException(PERSON_CAN_NOT_BE_NULL, HttpStatus.BAD_REQUEST);

        }

        Long personId = personDTO.getId();
        if(personId == null){
            logger.error("Person id cannot be null");
            throw new PersonIdCanNotBeNullException("Person id cannot be null", HttpStatus.BAD_REQUEST);

        }

        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new PersonNotFoundException(String.format(PERSON_NOT_FOUND, personId), HttpStatus.NOT_FOUND));


        if(personDTO.getFirstName() != null){
            person.setFirstName(personDTO.getFirstName());
        }

        if(personDTO.getLastName() != null){
            person.setLastName(personDTO.getLastName());
        }

        if(personDTO.getBirthDate() != null){
            person.setBirthDate(personDTO.getBirthDate());
        }

        if(personDTO.getEmail() != null){
            person.setEmail(personDTO.getEmail());
        }

        Person updatedPerson = personRepository.save(person);
        logger.info("Person updated with id : {}", updatedPerson.getId());

        cacheService.clearCache(CACHE_NAME);

        return PersonMapper.toDTO(updatedPerson);
    }

    @Override
    @CacheEvict(cacheNames = CACHE_NAME, allEntries = true)
    @CachePut(cacheNames = CACHE_NAME, key = "#result.id")
    public PersonDTO save(PersonDTO personDTO) {

        if(personDTO == null || personDTO.isEmpty()){
            logger.error(PERSON_CAN_NOT_BE_NULL);
            throw new PersonCanNotBeNullException(PERSON_CAN_NOT_BE_NULL, HttpStatus.BAD_REQUEST);

        }

        Person person = PersonMapper.toEntity(personDTO);
        person.setId(0L);
        Person savedPerson = personRepository.save(person);

        if(logger.isDebugEnabled()){
            logger.debug(String.format("Person saved with id : %d", savedPerson.getId()));
        }


        return PersonMapper.toDTO(savedPerson);
    }

    @Override
    @CacheEvict(cacheNames = CACHE_NAME, key = "#id")
    public void deleteById(Long id) {

        Person person = personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(String.format(PERSON_NOT_FOUND, id), HttpStatus.NOT_FOUND));

        logger.info("Person {} was deleted with id: {}", person.getFirstName() + " " + person.getLastName(), id);
        personRepository.deleteById(id);
    }


    public void simulateBackendCall() {
        try {
            logger.info("------------- Going to sleep for 5 seconds to simulate Backend Delay -----------");
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
