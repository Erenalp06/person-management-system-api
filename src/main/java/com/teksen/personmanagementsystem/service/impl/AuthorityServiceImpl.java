package com.teksen.personmanagementsystem.service.impl;

import com.teksen.personmanagementsystem.config.ROLE;
import com.teksen.personmanagementsystem.dto.AuthorityDTO;
import com.teksen.personmanagementsystem.dto.UserDTO;
import com.teksen.personmanagementsystem.entity.Authority;
import com.teksen.personmanagementsystem.entity.User;
import com.teksen.personmanagementsystem.exception.custom.DataAccessException;
import com.teksen.personmanagementsystem.exception.custom.authority.AuthorityCanNotBeNullException;
import com.teksen.personmanagementsystem.exception.custom.authority.AuthorityNotFoundException;
import com.teksen.personmanagementsystem.exception.custom.authority.IdCanNotBeNullException;
import com.teksen.personmanagementsystem.exception.custom.authority.InvalidRoleException;
import com.teksen.personmanagementsystem.exception.custom.user.UserHasNoRoleException;
import com.teksen.personmanagementsystem.exception.custom.user.UsernameCanNotBeNullException;
import com.teksen.personmanagementsystem.repository.AuthorityRepository;
import com.teksen.personmanagementsystem.service.AuthorityService;
import com.teksen.personmanagementsystem.service.UserService;
import com.teksen.personmanagementsystem.util.AuthorityMapper;
import com.teksen.personmanagementsystem.util.UserMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;
    private final Logger logger = LoggerFactory.getLogger(AuthorityServiceImpl.class);
    private static final String CACHE_NAME = "authorityCache";
    private final UserService userService;


    @Override
    @Cacheable(cacheNames = CACHE_NAME, key = "#username")
    public List<AuthorityDTO> findRolesByUsername(String username) {

        simulateSlowService();
        logger.info("Getting all roles from cache or database");

        UserDTO user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameCanNotBeNullException(String.format("Username not found with username %s", username), HttpStatus.NOT_FOUND);
        }

        Optional<List<Authority>> authorityOptionalList = authorityRepository.findByUserUsername(username);

        if(!(authorityOptionalList.isPresent() && !authorityOptionalList.get().isEmpty())){
            throw new UserHasNoRoleException(String.format("User has no role with username %s", username), HttpStatus.NOT_FOUND);
        }

        List<AuthorityDTO> authorityDTOList = authorityOptionalList
                .get()
                .stream()
                .map(AuthorityMapper::toDTO)
                .toList();

        logger.info("Retrieved all roles from database");
        return authorityDTOList;
    }

    @Override
    @CacheEvict(cacheNames = CACHE_NAME, allEntries = true)
    @CachePut(cacheNames = CACHE_NAME, key = "#authorityDTO.username")
    public AuthorityDTO save(AuthorityDTO authorityDTO) {

        UserDTO user = userService.findByUsername(authorityDTO.getUsername());
        if (user == null) {
            throw new UsernameCanNotBeNullException(String.format("Username not found with username %s", authorityDTO.getUsername()), HttpStatus.NOT_FOUND);
        }

        checkAuthorityDTOIsValid(authorityDTO);
        //authorityDTO.setId(0L);
        try {
            Authority authority = AuthorityMapper.toEntity(authorityDTO);
            authority.setId(0L);
            authority.setUser(UserMapper.toEntity(user));
            Authority savedAuthority = authorityRepository.save(authority);
            logger.info("Authority saved with id : {}", savedAuthority.getId());
            return AuthorityMapper.toDTO(savedAuthority);
        } catch (Exception e) {
            throw new DataAccessException("Authority could not be saved", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @CacheEvict(cacheNames = CACHE_NAME, allEntries = true)
    @CachePut(cacheNames = CACHE_NAME, key = "#authorityDTO.username")
    public AuthorityDTO update(AuthorityDTO authorityDTO) {
        checkAuthorityDTOIsValid(authorityDTO);

        if(authorityDTO.getId() == null){
            throw new IdCanNotBeNullException("Id can not be null", HttpStatus.BAD_REQUEST);

        }

        if(authorityDTO.getUsername() == null){
            throw new UsernameCanNotBeNullException("Username can not be null", HttpStatus.BAD_REQUEST);

        }

        Optional<List<Authority>> authorityOptionalList = authorityRepository.findByUserUsername(authorityDTO.getUsername());

        if(authorityOptionalList.isEmpty()){
            throw new UserHasNoRoleException(String.format("User has no role with username %s", authorityDTO.getUsername()), HttpStatus.NOT_FOUND);

        }

        List<Authority> authorityList = authorityOptionalList.get();

        if (authorityList.isEmpty()) {
            throw new UserHasNoRoleException("User has no role", HttpStatus.NOT_FOUND);
        }

        Optional<Authority> authorityOptional = authorityList.stream()
                .filter(authority -> authority.getId().equals(authorityDTO.getId()))
                .findFirst();

        if (authorityOptional.isEmpty()) {
            throw new AuthorityNotFoundException(String.format("Authority not found with id : %d", authorityDTO.getId()), HttpStatus.NOT_FOUND);
        }

        Authority authorityToUpdate = authorityOptional.get();

        checkIsValidRole(authorityDTO);

        if(authorityDTO.getRole() != null)
            authorityToUpdate.setRole(authorityDTO.getRole());

        try {
            authorityToUpdate = authorityRepository.save(authorityToUpdate);
            logger.info("Authority updated with id : {}", authorityToUpdate.getId());
            return AuthorityMapper.toDTO(authorityToUpdate);
        } catch (Exception e) {
            throw new AuthorityNotFoundException(String.format("Authority not found with id : %d", authorityDTO.getId()), HttpStatus.NOT_FOUND);
        }
    }

    private static void checkAuthorityDTOIsValid(AuthorityDTO authorityDTO) {
        if(authorityDTO == null || authorityDTO.isEmpty()){
            throw new AuthorityCanNotBeNullException("Authority can not be null", HttpStatus.BAD_REQUEST);
        }

        checkIsValidRole(authorityDTO);
    }

    private static void checkIsValidRole(AuthorityDTO authorityDTO) {
        String role = authorityDTO.getRole();

        boolean isRoleValid = Arrays.stream(ROLE.values())
                .map(Enum::name)
                .anyMatch(enumRole -> ("ROLE_" + enumRole).equals(role));

        if(!isRoleValid){
            throw new InvalidRoleException(String.format("Role is not valid : %s", role), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @CacheEvict(cacheNames = CACHE_NAME, allEntries = true)
    public String delete(Long id) {
        if(id == null){
            throw new IdCanNotBeNullException("Id can not be null", HttpStatus.BAD_REQUEST);
        }

        Optional<Authority> authorityOptional = authorityRepository.findById(id);

        if(authorityOptional.isEmpty()){
            throw new AuthorityNotFoundException(String.format("Authority not found with id : %d", id), HttpStatus.NOT_FOUND);
        }

        try {
            authorityRepository.delete(authorityOptional.get());
            logger.info("Authority deleted with id : {}", id);
            return "Authority deleted with id : " + id;
        } catch (Exception e) {
            throw new DataAccessException("Authority could not be deleted", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public void simulateSlowService() {
        try {
            logger.info("------------- Going to sleep for 5 seconds to simulate Backend Delay -----------");
            long time = 5000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
