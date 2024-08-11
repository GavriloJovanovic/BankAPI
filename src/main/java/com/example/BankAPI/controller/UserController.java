package com.example.BankAPI.controller;

import com.example.BankAPI.dto.UserDTO;
import com.example.BankAPI.model.User;
import com.example.BankAPI.model.View;
import com.example.BankAPI.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * UserController is responsible for handling HTTP requests related to User operations.
 * It provides endpoints for creating, retrieving, updating, and deleting users.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    /**
     * Constructor for UserController that initializes the UserService.
     *
     * @param userService the service used to handle user-related operations
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint to create a new user.
     *
     * @param userDTO the user data transfer object containing user information
     * @return a ResponseEntity containing the created User object
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        User createdUser = userService.createUser(userDTO);
        return ResponseEntity.ok(createdUser);
    }

    /**
     * Endpoint to retrieve a user by their ID.
     *
     * @param id the ID of the user to be retrieved
     * @return a ResponseEntity containing the UserDTO object with user details
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        return ResponseEntity.ok(userDTO);
    }

    /**
     * Endpoint to update an existing user by their ID.
     * Only public viewable fields are serialized due to the @JsonView annotation.
     *
     * @param id      the ID of the user to be updated
     * @param userDTO the user data transfer object containing updated user information
     * @return a ResponseEntity containing the updated User object
     */
    @PutMapping("/{id}")
    @JsonView(View.Public.class)
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        User updatedUser = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Endpoint to delete a user by their ID.
     *
     * @param id the ID of the user to be deleted
     * @return a ResponseEntity indicating the deletion was successful
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}