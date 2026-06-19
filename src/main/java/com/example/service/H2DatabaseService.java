package com.example.service;

import com.example.User;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class H2DatabaseService {

    private final String JDBC_URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
    private final String USER = "sa";
    private final String PASS = "";

    @PostConstruct
    public void initDatabase(){
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASS);
            Statement stmt = conn.createStatement()){

            stmt.execute("DROP TABLE IF EXISTS users");
            stmt.execute("CREATE TABLE users (id INT PRIMARY KEY, name VARCHAR(255))");
            stmt.execute("INSERT INTO users (id, name) VALUES (1, 'Alice')");
            stmt.execute("INSERT INTO users (id, name) VALUES (2, 'Bob')");
            stmt.execute("INSERT INTO users (id, name) VALUES (3, 'Charlie')");

            System.out.println("H2 database is initialized with 3 users.");
        } catch (Exception e) {
            System.err.println("Error initializing database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public int getUserCount(){
        int count = 0;
        try(Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASS);
        Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM users")){
            if (rs.next()){
                count = rs.getInt(1);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return count;
    }

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, name FROM users")){
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                users.add(new User(id, name));
            }
            System.out.println("getAllUsers() returned " + users.size() + " users");
        } catch (Exception e){
            System.err.println("Error getting users: " + e.getMessage());
            e.printStackTrace();
        }
        return users;
    }
}
