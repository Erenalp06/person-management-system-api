package com.teksen.personmanagementsystem.util;

import com.teksen.personmanagementsystem.dto.PersonDTO;
import com.teksen.personmanagementsystem.entity.Person;

import java.time.LocalDate;

public class PersonMapper {

    public static PersonDTO toDTO(Person person){
        if (person == null) {
            return null;
        }
        return PersonDTO.builder()
                .id(person.getId())
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .email(person.getEmail())
                .birthDate(person.getBirthDate())
                .build();
    }

    public static Person toEntity(PersonDTO personDTO){
        return Person.builder()
                .id(personDTO.getId())
                .firstName(personDTO.getFirstName())
                .lastName(personDTO.getLastName())
                .email(personDTO.getEmail())
                .birthDate(personDTO.getBirthDate())
                .build();
    }
}
