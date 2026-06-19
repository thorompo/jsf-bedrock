package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
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
 * REST Controller for User management API endpoints.
 * Provides CRUD operations for User entity.
 * Follows Single Responsibility Principle (SRP) - only handles HTTP requests/responses
 * Depends on UserService abstraction for business logic (Dependency Inversion)
 */
@Path("/users")
public class UserResource {

    @Inject
    private UserService userService;

    /**
     * GET /api/users - Get all users
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        System.out.println("UserResource.getAllUsers(): API request received");
        try {
            List<User> users = userService.getAllUsers();
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("count", users.size());
            response.put("data", users);
            return Response.ok(response).build();
        } catch (Exception e) {
            return buildErrorResponse("Failed to retrieve users", 500);
        }
    }

    /**
     * GET /api/users/{id} - Get user by ID
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("id") int id) {
        System.out.println("UserResource.getUserById(" + id + "): API request received");
        try {
            Optional<User> user = userService.getUserById(id);
            if (user.isPresent()) {
                Map<String, Object> response = new HashMap<>();
                response.put("status", "success");
                response.put("data", user.get());
                return Response.ok(response).build();
            } else {
                return buildErrorResponse("User not found with ID: " + id, 404);
            }
        } catch (Exception e) {
            return buildErrorResponse("Failed to retrieve user", 500);
        }
    }

    /**
     * GET /api/users/count - Get user count
     */
    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserCount() {
        System.out.println("UserResource.getUserCount(): API request received");
        try {
            int count = userService.getUserCount();
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("count", count);
            return Response.ok(response).build();
        } catch (Exception e) {
            return buildErrorResponse("Failed to count users", 500);
        }
    }

    /**
     * POST /api/users - Create new user
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(User user) {
        System.out.println("UserResource.createUser(): API request received for user: " + user);
        try {
            User savedUser = userService.saveUser(user);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "User created successfully");
            response.put("data", savedUser);
            return Response.status(Response.Status.CREATED).entity(response).build();
        } catch (Exception e) {
            return buildErrorResponse("Failed to create user", 500);
        }
    }

    /**
     * DELETE /api/users/{id} - Delete user by ID
     */
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") int id) {
        System.out.println("UserResource.deleteUser(" + id + "): API request received");
        try {
            boolean deleted = userService.deleteUser(id);
            if (deleted) {
                Map<String, Object> response = new HashMap<>();
                response.put("status", "success");
                response.put("message", "User deleted successfully");
                return Response.ok(response).build();
            } else {
                return buildErrorResponse("User not found with ID: " + id, 404);
            }
        } catch (Exception e) {
            return buildErrorResponse("Failed to delete user", 500);
        }
    }

    private Response buildErrorResponse(String message, int statusCode) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", "error");
        errorResponse.put("message", message);
        return Response.status(statusCode).entity(errorResponse).build();
    }
}

