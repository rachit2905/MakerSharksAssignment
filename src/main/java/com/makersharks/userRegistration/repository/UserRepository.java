package com.makersharks.userRegistration.repository;

import com.makersharks.userRegistration.pojo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for managing {@link UserInfo} entities.
 * This interface extends {@link JpaRepository} to provide CRUD operations and
 * custom query methods for the UserInfo entity.
 */
public interface UserRepository extends JpaRepository<UserInfo, Long> {

    /**
     * Custom query method to find a {@link UserInfo} entity by username.
     *
     * @param userName the username of the user to find.
     * @return an {@link Optional} containing the {@link UserInfo} if found, or empty if not found.
     */
    Optional<UserInfo> findByUserName(String userName);
}
