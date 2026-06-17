package com.example.service;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@ApplicationScoped
public class H2DatabaseService {

    private final String JDBC_URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
    private final String USER = "sa";
    private final String PASS = "";

    @PostConstruct
    public void initDatabase(){
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASS);
            Statement stmt = conn.createStatement()){

            stmt.execute("CREATE TABLE users (id INT PRIMARY KEY, name VARCHAR(255))");
            stmt.execute("INSERT INTO users (id, name) VALUES (1, 'Alice')");
            stmt.execute("INSERT INTO users (id, name) VALUES (2, 'Bob')");
            stmt.execute("INSERT INTO users (id, name) VALUES (3, 'Charlie')");

            System.out.println("H2 database is initialized with 3 users.");
        } catch (Exception e) {
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
}
