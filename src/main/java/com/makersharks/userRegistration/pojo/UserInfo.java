package com.makersharks.userRegistration.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a user information in the user registration system.
 * This class is annotated with JPA, Jakarta Bean Validation, Lombok, and Jackson annotations
 * to handle persistence, validation, object generation, and JSON serialization/deserialization.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {

    /**
     * The unique identifier for the user.
     * It is generated automatically by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The username of the user.
     * This field cannot be null.
     */
    @NotNull(message = "User name cannot be null")
    private String userName;

    /**
     * The email address of the user.
     * This field cannot be null and must be a valid email format.
     */
    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String email;

    /**
     * The password of the user.
     * This field cannot be null and must adhere to the specified pattern:
     * - At least 10 characters long
     * - Contains at least one uppercase letter
     * - Contains at least one lowercase letter
     * - Contains at least one number
     * - Contains at least one special character (@$!%*?&)
     * The password is only writable and will not be included in the JSON response.
     */
    @NotNull(message = "Password cannot be null")
    @Size(min = 10, message = "Password must be at least 10 characters long")
    @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{10,}$",
        message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character"
    )
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
