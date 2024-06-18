package com.makersharks.userRegistration.serviceImpl;

import com.makersharks.userRegistration.pojo.UserInfo;
import com.makersharks.userRegistration.repository.UserRepository;
import com.makersharks.userRegistration.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of the {@link UserService} interface for managing user registrations and retrievals.
 * This service class handles the business logic and interacts with the {@link UserRepository} for data access.
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

   

    /**
     * Registers a new user by saving the provided {@link UserInfo} object to the repository.
     *
     * @param userInfo the {@link UserInfo} object containing user details; must be valid.
     * @return the saved {@link UserInfo} object.
     */
    @Override
    public UserInfo registerUser(@Valid UserInfo userInfo) {
        try {
           
            UserInfo savedUser = userRepository.save(userInfo);
            log.info("User registered successfully: {}", savedUser);
            return savedUser;
        } catch (Exception e) {
            log.error("Error occurred while registering user: {}", e.getMessage());
            throw new RuntimeException("An error occurred while registering the user.", e);
        }
    }

    /**
     * Fetches a user by their username.
     *
     * @param userName the username of the user to fetch.
     * @return an {@link Optional} containing the {@link UserInfo} if found, or empty if not found.
     */
    @Override
    public Optional<UserInfo> fetchUserByUserName(String userName) {
        try {
            Optional<UserInfo> user = userRepository.findByUserName(userName);
            if (user.isPresent()) {
                log.info("User fetched successfully: {}", user.get());
            } else {
                log.warn("User not found with username: {}", userName);
            }
            return user;
        } catch (Exception e) {
            log.error("Error occurred while fetching user: {}", e.getMessage());
            throw new RuntimeException("An error occurred while fetching the user.", e);
        }
    }
}
