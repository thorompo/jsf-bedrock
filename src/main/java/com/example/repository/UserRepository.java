package com.example.repository;

import com.example.entity.User;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for User entity.
 * Defines contract for user data access operations.
 * Follows Interface Segregation Principle (ISP)
 */
public interface UserRepository {
    
    /**
     * Retrieve all users from the database.
     * @return List of all users
     */
    List<User> findAll();
    
    /**
     * Find a user by ID.
     * @param id User ID
     * @return Optional containing the user if found
     */
    Optional<User> findById(int id);
    
    /**
     * Get the total count of users.
     * @return Number of users in database
     */
    int count();
    
    /**
     * Save or update a user.
     * @param user User to save
     * @return Saved user with generated ID if applicable
     */
    User save(User user);
    
    /**
     * Delete a user by ID.
     * @param id User ID
     * @return true if user was deleted, false if not found
     */
    boolean deleteById(int id);
}

