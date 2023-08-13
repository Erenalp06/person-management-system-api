package com.teksen.personmanagementsystem.repository;

import com.teksen.personmanagementsystem.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {
    Optional<Person> findByEmail(String email);
}
