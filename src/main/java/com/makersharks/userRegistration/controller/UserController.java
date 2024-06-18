package com.makersharks.userRegistration.controller;

import com.makersharks.userRegistration.pojo.UserInfo;
import com.makersharks.userRegistration.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Registers a new user using the provided data in the POST request body.
     * Data must comply with Jakarta Bean Validation constraints defined in the UserInfo model.
     *
     * @param userInfo the {@link UserInfo} object to be registered; must be valid.
     * @return ResponseEntity containing the registered {@link UserInfo} and HTTP status OK.
     */
    @Operation(summary = "Register a new user", description = "Creates a new user entry in the database from the provided user data.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserInfo.class))),
            @ApiResponse(responseCode = "400", description = "Invalid user data provided", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error while registering the user")
    })
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserInfo userInfo) {
        try {
            UserInfo savedUser = userService.registerUser(userInfo);
            log.info("User registered successfully: {}", savedUser);
            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            log.error("Error occurred while registering user: {}", e.getMessage());
            return ResponseEntity.status(500).body("An error occurred while registering the user.");
        }
    }

    /**
     * Fetches user details by username.
     *
     * @param userName the username of the user to fetch details for; must be valid.
     * @return ResponseEntity containing the {@link UserInfo} if found, or HTTP status 404 if not found.
     */
    @Operation(summary = "Fetch user details by username", description = "Fetches the user details based on the provided username.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User fetched successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserInfo.class))),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error while fetching the user")
    })
    @GetMapping("/fetch")
    public ResponseEntity<?> fetchUser(@RequestParam String userName) {
        try {
            Optional<UserInfo> user = userService.fetchUserByUserName(userName);
            if (user.isPresent()) {
                log.info("User fetched successfully: {}", user.get());
                return ResponseEntity.ok(user.get());
            } else {
                log.warn("User not found with username: {}", userName);
                return ResponseEntity.status(404).body("User not found.");
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching user: {}", e.getMessage());
            return ResponseEntity.status(500).body("An error occurred while fetching the user.");
        }
    }

    /**
     * Custom exception handler for validation errors that may occur during user creation.
     * Provides detailed feedback on what fields failed validation.
     *
     * @param ex the captured validation error exception.
     * @return A ResponseEntity with detailed validation error messages in JSON format.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));
        log.error("Validation errors: {}", errors);
        return ResponseEntity.badRequest().body(errors);
    }
}
