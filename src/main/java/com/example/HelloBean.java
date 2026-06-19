package com.example;
import com.example.service.H2DatabaseService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class HelloBean {
    private String name = "Világ";
    private List<User> users;

    @Inject
    private H2DatabaseService databaseService;

    @PostConstruct
    public void init() {
        System.out.println("HelloBean initialized - loading users");
        users = databaseService.getAllUsers();
        System.out.println("HelloBean loaded " + (users != null ? users.size() : "null") + " users");
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public void greetings() { this.name = "Üdvözöllek, " + this.name + "!"; }

    public List<User> getUsers() {
        return users;
    }
}
