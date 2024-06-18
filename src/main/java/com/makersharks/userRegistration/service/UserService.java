package com.makersharks.userRegistration.service;

import java.util.Optional;

import com.makersharks.userRegistration.pojo.UserInfo;

import jakarta.validation.Valid;

/**
 * Service interface for managing user registrations and retrievals.
 * This interface defines the contract for business logic related to {@link UserInfo} entities.
 */
public interface UserService {

    /**
     * Registers a new user by saving the provided {@link UserInfo} object.
     * The user information must be validated before registration.
     *
     * @param userInfo the {@link UserInfo} object containing user details; must be valid.
     * @return the registered {@link UserInfo} object.
     */
    UserInfo registerUser(@Valid UserInfo userInfo);

    /**
     * Fetches a user by their username.
     *
     * @param userName the username of the user to fetch.
     * @return an {@link Optional} containing the {@link UserInfo} if found, or empty if not found.
     */
    Optional<UserInfo> fetchUserByUserName(String userName);
}
