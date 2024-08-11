package com.example.BankAPI.service;

import com.example.BankAPI.dto.UserDTO;
import com.example.BankAPI.model.User;
import com.example.BankAPI.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * UserService handles the business logic for user-related operations.
 * It interacts with the UserRepository to perform CRUD operations on users.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    /**
     * Encodes the given password using a custom encoding method.
     * The encoding shifts each character in the password by 15 positions in the ASCII table.
     *
     * @param password the plain text password to be encoded
     * @return the encoded password as a String
     */
    public String myEncoder(String password) {
        StringBuilder encoded = new StringBuilder();
        for (char c : password.toCharArray()) {
            encoded.append((char) (c + 15));
        }
        return encoded.toString();
    }

    /**
     * Constructor for UserService that initializes the UserRepository.
     *
     * @param userRepository the repository used for user data access
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Creates a new user based on the provided UserDTO.
     * The password is encoded before saving the user.
     *
     * @param userDTO the data transfer object containing user information
     * @return the created User object
     */
    public User createUser(UserDTO userDTO) {
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPasswordHash(myEncoder(userDTO.getPassword()));  // Hashing the password

        return userRepository.save(user);
    }
    /**
     * Retrieves a user by their ID.
     *
     * @param id the ID of the user to be retrieved
     * @return the User object if found
     * @throws UserNotFoundException if the user with the given ID does not exist
     */
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + id + " does not exist."));
    }

    /**
     * Updates an existing user based on the provided ID and user details.
     *
     * @param id          the ID of the user to be updated
     * @param userDetails the data transfer object containing updated user information
     * @return the updated User object
     * @throws UserNotFoundException if the user with the given ID does not exist
     */
    public User updateUser(Long id, UserDTO userDetails) {
        User existingUser = getUserById(id); // This will throw if user not found
        existingUser.setFirstName(userDetails.getFirstName());
        existingUser.setLastName(userDetails.getLastName());
        existingUser.setEmail(userDetails.getEmail());
        return userRepository.save(existingUser);
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id the ID of the user to be deleted
     * @throws UserNotFoundException if the user with the given ID does not exist
     */
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User with ID " + id + " does not exist to delete.");
        }
        userRepository.deleteById(id);
    }

}
