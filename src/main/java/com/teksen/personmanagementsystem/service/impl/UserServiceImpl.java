package com.teksen.personmanagementsystem.service.impl;

import com.teksen.personmanagementsystem.dto.UserDTO;
import com.teksen.personmanagementsystem.entity.User;
import com.teksen.personmanagementsystem.exception.custom.user.UserCanNotBeNullException;
import com.teksen.personmanagementsystem.exception.custom.user.UserNotFoundException;
import com.teksen.personmanagementsystem.exception.custom.user.UsernameCanNotBeNullException;
import com.teksen.personmanagementsystem.repository.UserRepository;
import com.teksen.personmanagementsystem.service.UserService;
import com.teksen.personmanagementsystem.util.UserMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final String CACHE_NAME = "userCache";
    private final CacheService cacheService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Cacheable(cacheNames = CACHE_NAME)
    public List<UserDTO> findAll() {
        simulateSlowService();
        logger.info("Getting all users from cache or database");

        List<UserDTO> users = userRepository.findAll()
                .stream()
                .map(UserMapper::toDTO)
                .toList();
        logger.info("Retrieved all users from database");
        return users;
    }

    @Override
    @Cacheable(cacheNames = CACHE_NAME, key = "#username")
    public UserDTO findByUsername(String username) {
        UserDTO userDTO = userRepository.findByUsername(username)
                .map(UserMapper::toDTO)
                .orElseThrow(() -> new UserNotFoundException(String.format("User not found with username %s", username), HttpStatus.NOT_FOUND));
        logger.info("User called with username : {}", username);
        return userDTO;

    }

    @Override
    @CacheEvict(cacheNames = CACHE_NAME, allEntries = true)
    @CachePut(cacheNames = CACHE_NAME, key = "#userDTO.username")
    public UserDTO save(UserDTO userDTO) {

        if(userDTO == null || userDTO.isEmpty()){
            logger.error("User can not be null");
            throw new UserCanNotBeNullException("User can not be null", HttpStatus.BAD_REQUEST);
        }

        String password = userDTO.getPassword();
        String cryptPassword = String.format("%s", bCryptPasswordEncoder.encode(password));

        userDTO.setPassword(cryptPassword);

        User user = userRepository.save(UserMapper.toEntity(userDTO));

        if(logger.isDebugEnabled()){
            logger.debug("User saved with username : {}", user.getUsername());
        }
        return UserMapper.toDTO(user);
    }

    @Override
    @CachePut(cacheNames = CACHE_NAME, key = "#userDTO.username")
    public UserDTO update(UserDTO userDTO) {

        if(userDTO == null || userDTO.isEmpty())
            throw new UsernameCanNotBeNullException("User can not be null", HttpStatus.BAD_REQUEST);


        User user = userRepository.findByUsername(userDTO.getUsername())
                .orElseThrow(() -> new UserNotFoundException(String.format("User not found with username %s", userDTO.getUsername()), HttpStatus.NOT_FOUND));

        if(userDTO.getPassword() != null){

            String password = userDTO.getPassword();
            String cryptPassword = String.format("{bcrypt}%s", bCryptPasswordEncoder.encode(password));
            userDTO.setPassword(cryptPassword);
            user.setPassword(userDTO.getPassword());
        }


        if(userDTO.getEnabled() != null){
            user.setEnabled(userDTO.getEnabled());
        }

        User updatedUser = userRepository.save(user);
        logger.info("User updated with username : {}", updatedUser.getUsername());

        cacheService.clearCache(CACHE_NAME);

        return UserMapper.toDTO(updatedUser);
    }

    @Override
    @CacheEvict(cacheNames = CACHE_NAME, key = "#username")
    public String deleteByUsername(String username) {
        if(username == null)
            throw new UsernameCanNotBeNullException("Username can not be null", HttpStatus.BAD_REQUEST);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(String.format("User not found with username %s", username), HttpStatus.NOT_FOUND));

        userRepository.delete(user);
        logger.info("User deleted with username : {}", username);
        return username;
    }

    public void simulateSlowService() {
        try {
            System.out.println("------------- Going to sleep for 5 seconds to simulate Backend Delay -----------");
            long time = 5000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
