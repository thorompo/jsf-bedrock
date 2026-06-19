package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * REST Controller for User-related API endpoints.
 * Follows Single Responsibility Principle (SRP) - only handles HTTP requests/responses
 * Depends on UserService abstraction for business logic (Dependency Inversion)
 */
@Path("/hello")
public class HelloResource {
    
    @Inject
    private UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGreeting() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Üdv az API-ról!");
        response.put("status", "success");
        response.put("timestamp", System.currentTimeMillis());
        return Response.ok(response).build();
    }
}


