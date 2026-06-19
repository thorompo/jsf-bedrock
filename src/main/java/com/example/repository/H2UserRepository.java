package com.example.repository;

import com.example.entity.User;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * H2 Database implementation of UserRepository.
 * Handles all database operations for User entity.
 * Follows Single Responsibility Principle (SRP) - only handles data access
 */
@ApplicationScoped
public class H2UserRepository implements UserRepository {

    private static final String JDBC_URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
    private static final String USER = "sa";
    private static final String PASS = "";

    @PostConstruct
    public void initDatabase() {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {

            stmt.execute("DROP TABLE IF EXISTS users");
            stmt.execute("CREATE TABLE users (id INT PRIMARY KEY, name VARCHAR(255))");
            stmt.execute("INSERT INTO users (id, name) VALUES (1, 'Alice')");
            stmt.execute("INSERT INTO users (id, name) VALUES (2, 'Bob')");
            stmt.execute("INSERT INTO users (id, name) VALUES (3, 'Charlie')");

            System.out.println("H2UserRepository: Database initialized with 3 users.");
        } catch (Exception e) {
            System.err.println("Error initializing database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, name FROM users")) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                users.add(new User(id, name));
            }
            System.out.println("H2UserRepository.findAll(): Retrieved " + users.size() + " users");
        } catch (Exception e) {
            System.err.println("Error retrieving users: " + e.getMessage());
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public Optional<User> findById(int id) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, name FROM users WHERE id = " + id)) {
            if (rs.next()) {
                User user = new User(rs.getInt("id"), rs.getString("name"));
                return Optional.of(user);
            }
        } catch (Exception e) {
            System.err.println("Error retrieving user by ID: " + e.getMessage());
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public int count() {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM users")) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.err.println("Error counting users: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public User save(User user) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            if (user.getId() == 0) {
                // Insert new user
                stmt.execute("INSERT INTO users (name) VALUES ('" + user.getName() + "')");
            } else {
                // Update existing user
                stmt.execute("UPDATE users SET name = '" + user.getName() + "' WHERE id = " + user.getId());
            }
            System.out.println("H2UserRepository.save(): User saved: " + user);
        } catch (Exception e) {
            System.err.println("Error saving user: " + e.getMessage());
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean deleteById(int id) {
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {
            int rowsAffected = stmt.executeUpdate("DELETE FROM users WHERE id = " + id);
            System.out.println("H2UserRepository.deleteById(): Deleted " + rowsAffected + " user(s)");
            return rowsAffected > 0;
        } catch (Exception e) {
            System.err.println("Error deleting user: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
}

