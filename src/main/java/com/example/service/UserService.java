package com.example.service;

import com.example.entity.User;
import java.util.List;
import java.util.Optional;

/**
 * UserService interface - defines business logic contract for user operations.
 * Follows Interface Segregation Principle (ISP) - clients depend on this interface, not implementation
 * Follows Dependency Inversion Principle (DIP) - depends on abstractions, not concrete classes
 */
public interface UserService {
    
    /**
     * Get all users.
     * @return List of all users
     */
    List<User> getAllUsers();
    
    /**
     * Get a user by ID.
     * @param id User ID
     * @return User if found
     */
    Optional<User> getUserById(int id);
    
    /**
     * Get the total count of users in the system.
     * @return Total user count
     */
    int getUserCount();
    
    /**
     * Create or update a user.
     * @param user User to save
     * @return Saved user
     */
    User saveUser(User user);
    
    /**
     * Delete a user by ID.
     * @param id User ID
     * @return true if deletion was successful
     */
    boolean deleteUser(int id);
}

