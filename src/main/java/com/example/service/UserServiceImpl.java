package com.example.service;

import com.example.entity.User;
import com.example.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * UserService implementation - contains business logic for user operations.
 * Follows Single Responsibility Principle (SRP) - only handles business logic
 * Depends on UserRepository abstraction for data access (Dependency Inversion)
 */
@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        System.out.println("UserServiceImpl.getAllUsers(): Fetching all users from repository");
        List<User> users = userRepository.findAll();
        System.out.println("UserServiceImpl.getAllUsers(): Found " + users.size() + " users");
        return users;
    }

    @Override
    public Optional<User> getUserById(int id) {
        System.out.println("UserServiceImpl.getUserById(): Fetching user with ID " + id);
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            System.out.println("UserServiceImpl.getUserById(): User found: " + user.get());
        } else {
            System.out.println("UserServiceImpl.getUserById(): User not found with ID " + id);
        }
        return user;
    }

    @Override
    public int getUserCount() {
        System.out.println("UserServiceImpl.getUserCount(): Counting users");
        int count = userRepository.count();
        System.out.println("UserServiceImpl.getUserCount(): Total users: " + count);
        return count;
    }

    @Override
    public User saveUser(User user) {
        System.out.println("UserServiceImpl.saveUser(): Saving user: " + user);
        User savedUser = userRepository.save(user);
        System.out.println("UserServiceImpl.saveUser(): User saved successfully");
        return savedUser;
    }

    @Override
    public boolean deleteUser(int id) {
        System.out.println("UserServiceImpl.deleteUser(): Deleting user with ID " + id);
        boolean deleted = userRepository.deleteById(id);
        if (deleted) {
            System.out.println("UserServiceImpl.deleteUser(): User deleted successfully");
        } else {
            System.out.println("UserServiceImpl.deleteUser(): User not found for deletion");
        }
        return deleted;
    }
}

