package com.example.bean;

import com.example.entity.User;
import com.example.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.List;

/**
 * JSF Managed Bean for UI interaction.
 * Follows Single Responsibility Principle (SRP) - only handles presentation logic
 * Depends on UserService abstraction for business logic (Dependency Inversion)
 */
@Named
@RequestScoped
public class HelloBean {
    private String name = "Világ";
    private List<User> users;

    @Inject
    private UserService userService;

    @PostConstruct
    public void init() {
        System.out.println("HelloBean initialized - loading users from service");
        users = userService.getAllUsers();
        System.out.println("HelloBean loaded " + (users != null ? users.size() : "null") + " users");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String greetings() {
        return "Üdvözöllek, " + this.name + "!";
    }

    public List<User> getUsers() {
        return users;
    }
}
