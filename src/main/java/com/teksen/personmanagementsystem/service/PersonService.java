package com.teksen.personmanagementsystem.service;

import com.teksen.personmanagementsystem.dto.PersonDTO;
import com.teksen.personmanagementsystem.entity.Person;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PersonService {

    List<PersonDTO> findAll();
    PersonDTO findById(Long id);
    PersonDTO update(PersonDTO personDTO);
    PersonDTO save(PersonDTO personDTO);
    void deleteById(Long id);

}
