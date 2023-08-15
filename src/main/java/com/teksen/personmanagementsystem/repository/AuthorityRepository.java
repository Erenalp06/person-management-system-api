package com.teksen.personmanagementsystem.repository;

import com.teksen.personmanagementsystem.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Long>{
    Optional<List<Authority>> findByUserUsername(String username);
}
